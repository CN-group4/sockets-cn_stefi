import socket

HOST = "0.0.0.0"
PORT = 5050

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen(1)

print(f"Server pornit pe portul {PORT}. Aștept client...")
conn, addr = server.accept()
print("Client conectat:", addr)

while True:
    data = conn.recv(1024).decode("utf-8")
    if not data:
        break

    print("Client:", data)

    if data.lower() == "exit":
        break

    mesaj = input("Server: ")
    conn.send(mesaj.encode("utf-8"))

    if mesaj.lower() == "exit":
        break

conn.close()
server.close()
print("Conexiune închisă.")