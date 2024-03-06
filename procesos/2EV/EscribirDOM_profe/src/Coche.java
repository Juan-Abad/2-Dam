public class Coche {
	private String marca, color;

	

	public Coche(String marca, String color) {
		super();
		this.marca = marca;
		this.color = color;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", color=" + color + "]";
	}

}
