# python b.py
import oracledb
import os

# Configurar la variable de entorno TNS_ADMIN
os.environ["TNS_ADMIN"] = "Wallet_developer"  # Ajusta la ruta según tu sistema

# Conectar con el alias del servicio en tnsnames.ora
conn = oracledb.connect(user="DEVELOPER", password="CondoriJara2020@", dsn="developer_medium")

# Crear un cursor y ejecutar una consulta
cursor = conn.cursor()
cursor.execute("SELECT * FROM CLIENTE")

# Mostrar resultados
for row in cursor:
    print(row)

# Cerrar la conexión
cursor.close()
conn.close()
