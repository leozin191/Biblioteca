import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

	public static Hashtable<Integer, Clientes> loadclientes() throws IOException {
		MyIO.setCharset("UTF-8");
		Hashtable<Integer, Clientes> tabela = new Hashtable<Integer, Clientes>();
		try {
			File file = new File("c:\\users\\leona\\downloads\\clientes.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String frase;

			while ((frase = br.readLine()) != null) {
				String[] dados = frase.split(";");
				if (dados.length > 1) {
					try {
						Clientes a = new Clientes(Integer.parseInt(dados[0]), dados[1]);
						tabela.put(a.getMatricula(), a);

					} catch (Exception e) {
						System.out.print("Erro ao construir linha " + frase);
						for (int i = 0; i < dados.length; i++) {
							System.out.print("\n" + dados[i]);
						}
					}

				}
			}
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabela;
	}

	public static Hashtable<Integer, Livros> loadlivros() throws IOException {
		MyIO.setCharset("UTF-8");
		Hashtable<Integer, Livros> tabela = new Hashtable<Integer, Livros>();
		try {
			File file = new File("c:\\users\\leona\\downloads\\livros.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String frase;

			while ((frase = br.readLine()) != null) {
				String[] dados = frase.split(";");
				if (dados.length > 1) {
					try {
						Livros a = new Livros(Integer.parseInt(dados[0]), dados[1]);
						tabela.put(a.getCodigo(), a);
					} catch (Exception e) {
						System.out.print("Erro ao construir linha " + frase);
						for (int i = 0; i < dados.length; i++) {
							System.out.print("\n" + dados[i]);
						}
					}

				}
			}
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabela;
	}

	public static void main(String[] args) throws IOException {
		Hashtable<Integer, Livros> tabelaLivro = loadlivros();
		Hashtable<Integer, Clientes> tabelaCliente = loadclientes();
		int input = -1;
		Scanner sc = new Scanner(System.in);
		Clientes c;
		int matricula;
		while (input != 0) {
			System.out.println("1 - Informar matricula de cliente");
			System.out.println("2 - Consultar livro mais alugado");
			System.out.println("3 - Consultar cliente mais frequente");
			System.out.println("4 - Consultar ultimo livro alugado");
			System.out.println("5 - Salvar dados de alugueis em binário");

			input = sc.nextInt();
			switch (input) {
			case 1: {
				matricula = sc.nextInt();
				c = tabelaCliente.get(matricula);
				if (c != null) {
					while (input != 5) {
						System.out.println("1 - Listar livros alugados");
						System.out.println("2 - Devolver livro");
						System.out.println("3 - Alugar livro");
						System.out.println("4 - Estender aluguel");
						System.out.println("5 - Sair");
						input = sc.nextInt();
						switch (input) {
						case 1: {
							c.printLivros();
							break;
						}
						case 2: {
							c.devolver(sc);
							break;
						}
						case 3: {
							int codigo;
							System.out.println("Informe o código do livro para alugar:");
							codigo = sc.nextInt();
							Livros l = tabelaLivro.get(codigo);
							if (l != null) {
								l.alugar(c);

							}
							break;

						}
						case 4: {
							int codigo;
							System.out.println("Informe o código do livro para estender o aluguel:");
							codigo = sc.nextInt();
							Livros l = tabelaLivro.get(codigo);
							if (l != null) {
								l.estender();
							}
							break;
						}
						default:
						}
					}
				}
				break;
			}

			case 2: {
				if (Livros.maisAlugado != null) {
					Livros.maisAlugado.print();
				} else {
					System.out.println("Nenhum livro ainda foi alugado.");
				}
				break;
			}
			case 3: {
				if (Clientes.maisFrequente != null) {
					Clientes.maisFrequente.print();
				} else {
					System.out.println("Nenhum livro ainda foi alugado.");
				}
				break;
			}
			case 4: {
				if (Livros.ultimoAlugado != null) {
					Livros.ultimoAlugado.print();
				} else {
					System.out.println("Nenhum livro ainda foi alugado.");
				}
				break;
			}
			case 5: {
				File file = new File("EmprestimosLivros.bin");
//				byte[] data = "ABCD".getBytes(StandardCharsets.UTF_8);
				try (FileOutputStream fos = new FileOutputStream(file)) {
					for (int k : tabelaCliente.keySet()) {
						for (Livros l : tabelaCliente.get(k).getAlugados()) {
							byte[] data = l.toString().getBytes(StandardCharsets.UTF_8);

							fos.write(data);

						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			default:
			}
		}

	}

}
