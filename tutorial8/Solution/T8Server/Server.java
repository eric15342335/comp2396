import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private SharedNumber number;

	// The set of all the print writers for all the clients, used for broadcast.
	private Set<PrintWriter> writers = new HashSet<>();

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.number = new SharedNumber();
	}

	public void start() {
		var pool = Executors.newFixedThreadPool(200);
		int clientCount = 0;
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				pool.execute(new Handler(socket));
				System.out.println("Connected to client " + clientCount++);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public class Handler implements Runnable {
		private Socket socket;
		private Scanner input;
		private PrintWriter output;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("Connected: " + socket);
			try {
				input = new Scanner(socket.getInputStream());
				output = new PrintWriter(socket.getOutputStream(), true);

				// add this client to the broadcast list
				writers.add(output);

				while (input.hasNextLine()) {
					var command = input.nextLine();

					System.out.println("Server Received: " + command);

					if (command.startsWith("UP")) {
						number.up();
					} else if (command.startsWith("DOWN")) {
						number.down();
					}

					// broadcast updated number to all clients
					for (PrintWriter writer : writers) {
						writer.println(number.getNumber());
					}

					System.out.println("Server Broadcasted: " + number.getNumber());

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				// client disconnected
				if (output != null) {
					writers.remove(output);
				}
			}
		}
	}
}
