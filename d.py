import oracledb
import os

# Configurar la variable de entorno TNS_ADMIN con la ruta correcta
os.environ["TNS_ADMIN"] = "Wallet_developer"  # Ajusta esta ruta

# Conectar a Oracle Autonomous Database
conn = oracledb.connect(
    user="DEVELOPER",
    password="CondoriJara2020@",
    dsn="developer_medium"  # Solo el nombre del alias en tnsnames.ora
)

# Crear cursor y ejecutar una consulta de prueba
cursor = conn.cursor()
cursor.execute("SELECT 'Conexión exitosa' FROM dual")

# Mostrar resultado
for row in cursor:
    print(row)

# Cerrar cursor y conexión
cursor.close()
conn.close()
