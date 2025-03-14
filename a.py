# export TNS_ADMIN=Wallet_developer
# echo $TNS_ADMIN
# python a.py
import oracledb

# Configuración de conexión
dsn = "(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=developer_medium)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=Wallet_developer)))"
username = "DEVELOPER"
password = "CondoriJara2020@"
wallet_path = "Wallet_developer"  # Asegúrate de tener la wallet descargada

# Conectar a la base de datos
#oracledb.init_oracle_client(lib_dir=wallet_path)  # Solo si usas un cliente instantáneo de Oracle

conn = oracledb.connect(user=username, password=password, dsn=dsn)

# Crear un cursor y ejecutar una consulta
cursor = conn.cursor()
cursor.execute("SELECT * FROM CLIENTE")
for row in cursor:
    print(row)

# Cerrar la conexión
cursor.close()
conn.close()
