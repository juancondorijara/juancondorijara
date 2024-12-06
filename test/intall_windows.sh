#!/bin/bash

# Configuración inicial
CONFIG_FILE="$HOME/.kube/config"
TEMP_CONFIG_FILE="./kube_config_container"
#VALIDATOR_IMAGE="kubectl-make"
VALIDATOR_IMAGE="gova731/lab-k8s-validator-windows"
DOCKER_PORT=00
HOST_IP="host.docker.internal"
PROXY_PID=""
STUDENT_EMAIL_FILE="/tmp/student_email.txt"

# Función para obtener el correo
get_student_email() {
    # Leer el correo del archivo si ya existe
    if [[ -f "$STUDENT_EMAIL_FILE" ]]; then
        STUDENT_EMAIL=$(cat "$STUDENT_EMAIL_FILE")
        echo "Usando correo previamente guardado: $STUDENT_EMAIL"
    else
        # Solicitar el correo si no está guardado
        echo -n "Ingresa tu correo institucional (terminado en vallegrande.edu.pe): "
        read STUDENT_EMAIL
        if [[ ! "$STUDENT_EMAIL" =~ ^[a-zA-Z0-9._%+-]+@vallegrande\.edu\.pe$ ]]; then
            echo "Error: El correo debe ser válido y del dominio vallegrande.edu.pe."
            exit 1
        fi
        echo "$STUDENT_EMAIL" > "$STUDENT_EMAIL_FILE"
        echo "Correo capturado: $STUDENT_EMAIL"
    fi
}

# Función para iniciar el proxy de kubectl
start_kubectl_proxy() {
    echo "Iniciando kubectl proxy..."
    kubectl proxy --address='0.0.0.0' --accept-hosts='.*' > proxy_output.log 2>&1 &
    PROXY_PID=$!
    echo "Proxy iniciado con PID: $PROXY_PID"
    sleep 3  # Esperar a que el proxy esté listo

    # Extraer el puerto desde la salida
    DOCKER_PORT=$(grep -oP 'Starting to serve on \[::\]:(\d+)' proxy_output.log | grep -oP '\d+')
    if [[ -z "$DOCKER_PORT" ]]; then
        echo "Error: No se pudo determinar el puerto de kubectl proxy."
        stop_kubectl_proxy
        exit 1
    fi
    echo "Proxy escuchando en el puerto $DOCKER_PORT."
}

# Función para detener el proxy de kubectl
stop_kubectl_proxy() {
    if [[ -n "$PROXY_PID" ]]; then
        echo "Deteniendo kubectl proxy..."
        kill "$PROXY_PID"
        echo "Proxy detenido."
    fi
}

# Función para actualizar el archivo kubeconfig
update_kubeconfig() {
    echo "Creando configuración temporal para Kubernetes..."
    cp "$CONFIG_FILE" "$TEMP_CONFIG_FILE"
    sed -i "s|server: https://.*|server: http://$HOST_IP:$DOCKER_PORT|g" "$TEMP_CONFIG_FILE"
    echo "Archivo kubeconfig actualizado para contenedor."
}

# Función para validar acceso al namespace desde un contenedor
validate_namespace() {
    echo "Verificando conexión al clúster y namespace..."
    
    CONFIG_PATH=$(cygpath -w "$(pwd)/kube_config_container" | sed 's/\\/\//g')

    docker run --rm -it \
        -v "$CONFIG_PATH:/root/.kube/config:ro" \
        -e STUDENT_EMAIL="$STUDENT_EMAIL" \
        $VALIDATOR_IMAGE kubectl get ns
    if [[ $? -ne 0 ]]; then
        echo "Error: No se pudo acceder al namespace."
        stop_kubectl_proxy
        exit 1
    fi
    echo "Conexión al namespace validada."
}

# Función para ejecutar el validador
execute_validator() {
    echo "Ejecutando validador..."
    
    CONFIG_PATH=$(cygpath -w "$(pwd)/kube_config_container" | sed 's/\\/\//g')

    docker run --rm -it \
        -v "$CONFIG_PATH:/root/.kube/config:ro" \
        -e STUDENT_EMAIL="$STUDENT_EMAIL" \
        $VALIDATOR_IMAGE make validate
    if [[ $? -ne 0 ]]; then
        echo "Error: El validador encontró un problema."
        stop_kubectl_proxy
        exit 1
    fi
    echo "Validación completada exitosamente."
}

# Flujo principal del script
main() {
    get_student_email    # Obtener el correo antes de iniciar cualquier proceso
    start_kubectl_proxy
    update_kubeconfig
    validate_namespace
    execute_validator
    stop_kubectl_proxy
}

# Ejecutar flujo principal
main
