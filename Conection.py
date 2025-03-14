# pip install oracledb
# python Conection.py
import oracledb  

# Conectar a Oracle XE en localhost
conn = oracledb.connect(
    user="system",
    password="1234",
    dsn="localhost:1521/XEPDB1"  # Reemplaza "XEPDB1" con "XE" si es necesario
)

# Crear cursor y ejecutar una consulta de prueba
cursor = conn.cursor()
cursor.execute("SELECT * FROM SYSTEM.CUSTOMER")

# Obtener y mostrar los registros
rows = cursor.fetchall()

if rows:
    for row in rows:
        print(row)  # Imprime cada fila de la tabla
else:
    print("No hay registros en la tabla CUSTOMER.")

# Cerrar cursor y conexión
cursor.close()
conn.close()
