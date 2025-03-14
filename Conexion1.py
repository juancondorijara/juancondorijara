# pip install cx_Oracle
# python Conexion1.py
# export TNS_ADMIN=Wallet_developer
import cx_Oracle

# Ruta a la carpeta donde está la Wallet
TNS_ADMIN = "/Wallet_developer"  # Cambia esto a la ubicación de tu Wallet

# Datos de conexión
USER = "DEVELOPER"
PASSWORD = "CondoriJara2020@"
SERVICE_NAME = "developer_medium"  # Nombre del servicio de la BD

# Construir la cadena de conexión
dsn = cx_Oracle.makedsn(None, None, service_name=SERVICE_NAME)

# Establecer la conexión con la base de datos
try:
    conn = cx_Oracle.connect(user=USER, password=PASSWORD, dsn=dsn, encoding="UTF-8")
    print("✅ Conexión exitosa a Oracle Autonomous Database")

    # Crear un cursor
    cursor = conn.cursor()

    # Ejecutar una consulta de prueba
    cursor.execute("SELECT * FROM CLIENTE")
    for row in cursor:
        print("Fecha actual en Oracle:", row[0])

    # Cerrar la conexión
    cursor.close()
    conn.close()

except cx_Oracle.Error as e:
    print("❌ Error al conectar a la base de datos:", e)
