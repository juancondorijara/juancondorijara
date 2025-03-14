# python c.py
# docker exec -it oracle-docker sqlplus system/ora1234@localhost:1521/XEPDB1
import oracledb  

# Conectar a Oracle XE en localhost
conn = oracledb.connect(
    user="system",
    password="ora1234",
    dsn="localhost:1521/XEPDB1"  # Reemplaza "XEPDB1" con "XE" si es necesario
)

# Crear cursor y ejecutar una consulta de prueba
cursor = conn.cursor()
cursor.execute("SELECT * FROM SYSTEM.CUSTO")

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
