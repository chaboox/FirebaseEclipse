package model;

/**
 * @author mcq-1
 *
 */
public class Article {
	
private int id;
private String name;
private double price;

public Article() {
}

public Article(String name, double price) {
	this.name = name;
	this.price = price;
}


/**
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return the price
 */
public double getPrice() {
	return price;
}

/**
 * @param price the price to set
 */
public void setPrice(double price) {
	this.price = price;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return  name + " " + price + " €";
}



}
