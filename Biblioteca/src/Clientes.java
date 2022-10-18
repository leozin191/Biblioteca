import java.util.ArrayList;
import java.util.Scanner;

public class Clientes {
	static Clientes maisFrequente;
	private int frequencia;
	private int matricula;
	private String nome;
	private ArrayList<Livros> alugados;

	Clientes(int matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
		this.alugados = new ArrayList<Livros>();
		this.frequencia = 0;
	}

	public ArrayList<Livros> getAlugados() {
		return this.alugados;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void printLivros() {
		for (Livros l : this.alugados) {
			l.print();
		}
	}

	public void devolver(Scanner sc) {
		System.out.println("Informe o código do livro para devolução:");
		int codigo = sc.nextInt();
		for (Livros l : this.alugados) {
			if (l.getCodigo() == codigo) {
				this.alugados.remove(l);
				l.devolvido();
			}
		}

	}

	public void addlivro(Livros livros) {
		this.alugados.add(livros);
		this.frequencia++;
		if (Clientes.maisFrequente == null) {
			Clientes.maisFrequente = this;
		}
		if (this.frequencia > Clientes.maisFrequente.getFrequencia()) {
			Clientes.maisFrequente = this;
		}

	}

	private int getFrequencia() {
		return this.frequencia;
	}

	public void print() {
		System.out.printf("Matrícula: %d Nome: %s Frequência: %d\n", this.matricula, this.nome, this.frequencia);

	}

}
