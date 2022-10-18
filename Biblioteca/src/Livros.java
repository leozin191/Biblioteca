import java.util.Date;

public class Livros {
	static Livros ultimoAlugado;
	static Livros maisAlugado;
	private Date devolucao;
	private Date alugadoEm;
	private Clientes alugador;
	private int codigo;
	private String titulo;
	private int vezesAlugado;

	Livros(int codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.devolucao = null;
		this.alugadoEm = null;
		this.alugador = null;
		this.vezesAlugado = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void print() {
		System.out.printf("Código: %d Titulo: %s\n", this.codigo, this.titulo);
		if (this.alugadoEm != null) {
			System.out.println("Alugado em: " + this.alugadoEm);
		}

	}

	public void alugar(Clientes c) {
		if (this.alugador == null) {
			this.alugador = c;
			c.addlivro(this);
			this.vezesAlugado++;
			if (Livros.maisAlugado == null) {
				Livros.maisAlugado = this;
			}
			if (this.vezesAlugado > Livros.maisAlugado.getVezesAlugado()) {
				Livros.maisAlugado = this;
			}
			this.alugadoEm = new Date();
			Livros.ultimoAlugado = this;
			System.out.println("Livro " + this.getTitulo() + " alugado com sucesso. ");
		} else {
			System.out.println("Livro já está alugado.");
		}

	}

	private int getVezesAlugado() {
		return this.vezesAlugado;
	}

	public void devolvido() {
		this.alugador = null;
		this.alugadoEm = null;

	}

	public void estender() {
		this.alugadoEm = new Date();

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.titulo + " alugado por " + this.alugador.getNome() + "\n";
	}
}
