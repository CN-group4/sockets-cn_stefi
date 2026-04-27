import socket

HOST = "0.0.0.0"
PORT = 5051

server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server.bind((HOST, PORT))

print(f"Server UDP pornit pe portul {PORT}...")

client_addr = None

while True:
    data, addr = server.recvfrom(1024)

    mesaj = data.decode("utf-8")
    print("Client:", mesaj)

    if client_addr is None:
        client_addr = addr

    if mesaj.lower() == "exit":
        break

    raspuns = input("Server: ")
    server.sendto(raspuns.encode("utf-8"), client_addr)

    if raspuns.lower() == "exit":
        break

server.close()
print("Server oprit.")