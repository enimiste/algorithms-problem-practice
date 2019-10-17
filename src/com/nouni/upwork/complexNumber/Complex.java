package com.nouni.upwork.complexNumber;

public class Complex {

	static final Complex I = new Complex(0, 1);
	static final Complex ZERO = new Complex(0, 0);
	static final Complex ONE = new Complex(1, 0);

	protected double x;// represents the real part
	protected double y;// represents the imaginary part

	public Complex() {
		this(1, 0.0);
	}

	/**
	 * 
	 * @param a real part
	 * @param b imaginary part
	 */
	public Complex(double a, double b) {
		this.x = a;
		this.y = b;
	}

	/**
	 * Adds two complex numbers
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex add(Complex c) {
		if (c == null)
			throw new IllegalArgumentException("Cannot add a complex number to a null one.");
		return new Complex(x + c.x, y + c.y);
	}

	/**
	 * Adds this complex to a numeric value.
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex add(double a) {
		return new Complex(x + a, y);
	}

	/**
	 * Multiply two complex numbers
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex mult(Complex c) {
		// (x + iy)(a + ib) = xa + ixb + iya -yb=(xa -yb) + i(xb+ya)
		if (c == null)
			throw new IllegalArgumentException("Cannot multiply a complex number to a null one.");
		return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
	}

	/**
	 * Multiply this complex to a numeric value
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex mult(double a) {
		return new Complex(x * a, y * a);
	}

	/**
	 * Conjugate = x - i * y
	 * 
	 * @param c
	 * @return a new complex representing the conjugate of this complex
	 */
	public Complex conjugate() {
		return new Complex(x, -y);
	}

	/**
	 * -C = C * -1
	 * 
	 * @param c
	 * @return a new complex representing the negated value of this complex
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex negate() {
		return mult(-1);
	}

	/**
	 * Calculate the reciprocal value of this complex : 1/C
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws ArithmeticException if the parameter is the ZERO complex
	 */
	public Complex reciprocal() {
		if (isEqual(ZERO))
			throw new ArithmeticException("Reciprocal of a zero complex number not allowed.");
		double z = x * x + y * y;
		return new Complex(x / z, -y / z);
	}

	/**
	 * Divide this complex by another one
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public Complex div(Complex by) {
		if (by == null)
			throw new IllegalArgumentException("Cannot divide a complex by a null one.");
		return mult(by.reciprocal());
	}

	/**
	 * Divide this complex by numeric value
	 * 
	 * @param c
	 * @return a new complex representing the result
	 * @throws ArithmeticException if the parameter is the equals to zero
	 */
	public Complex div(double by) {
		return mult(1 / by);
	}

	/**
	 * 
	 * @return the real component of this complex
	 */
	public double getReal() {
		return x;
	}

	/**
	 * 
	 * @return the imaginary component of this complex
	 */
	public double getImaginary() {
		return y;
	}

	/**
	 * Check equality between this complex and the one passed as parameter Equal if
	 * this.x==c.x and this.y==c.y
	 * 
	 * @param c
	 * @return true if they real/imaginary component are equal
	 * @throws IllegalArgumentException if the parameter is null. Unspecified case
	 */
	public boolean isEqual(Complex c) {
		if (c == null)
			throw new IllegalArgumentException("Cannot compare a complex to a null one.");
		if (c.x == x && c.y == y)
			return true;
		else
			return false;
	}

	/**
	 * Returns a string representation of this complex : x + i*y. If y is negatif
	 * the format is : x - i*y' where y' is the absolute value of y. The x and y
	 * double values are formated with 2 digits before the ',' (%.2f). Exemple : -
	 * (new Complex(10, 2.5)).toString() will returns 10.00 + i * 2.50 and (new
	 * Complex(10, -2.5)).toString() will returns 10.00 - i * 2.50
	 */
	@Override
	public String toString() {
		String op = String.valueOf(y).contains("-") ? "-" : "+";// y >= 0 ? "+" : "-"; doesn't working

		return String.format("%.2f %s i*%.2f", x, op, Math.abs(y));
	}

	// Test
	//To be removed
	public static void main(String[] args) {
		Complex[] nbrs = { new Complex(), new Complex(10, 0.5), new Complex(0, 0.5), };

		System.out.println(I.isEqual(ONE));
		System.out.println(ONE.isEqual(new Complex(1, 0)));
		System.out.println("1 / i : \t" + ONE.div(I));
		System.out.println("");
		for (Complex c : nbrs) {
			System.out.println("C : \t\t" + c);
			System.out.println("1/C : \t\t" + c.reciprocal());
			System.out.println("C * 1/C : \t" + c.reciprocal().mult(c));
			System.out.println("C / C : \t" + c.div(c));
			System.out.println("C / i : \t" + c.div(I));
			System.out.println("C Negate : \t" + c.negate());
			System.out.println("[C] : \t\t" + c.conjugate());
			System.out.println("C + C : \t" + c.add(c));
			System.out.println("C + 100 : \t" + c.add(100));
			System.out.println("C * C : \t" + c.mult(c));
			System.out.println("C * 0.3 : \t" + c.mult(0.3));
			System.out.println("C * [C] : \t" + c.mult(c.conjugate()));
			System.out.println("");
		}
	}
}
