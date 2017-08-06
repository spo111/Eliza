import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Name:  Yulian Zhou, Zheng Kuang
 * Email: zhouyulian17@gmail.com, kuangzheng04@gmail.com
 *
 * Compilation:  javac Key.java
 * Execution:    none
 * Dependencies: none
 *
 * Description:  A data type that stores a key word and its decomposition
 *               patterns. 
 *
 *************************************************************************/

public class Key implements Comparable<Key>{
	
	private String key;             // key word
	private int rank;               // rank of key
	private List<Decomp> decomp;    // list of decomp
	
	public Key(String k, int n) {
		key = k;
		rank = n;
		decomp = new ArrayList<Decomp>();
	}
	
	// returns key String
	public String getKey() { return key; }
	
	// returns decomp of key
	public List<Decomp> getDecomp() { return decomp; }
	
	// adds decomp to decomp list
	public void addDecomp(Decomp d) { decomp.add(d); }
	
	public int compareTo(Key other) { return other.rank - this.rank; }
	
	// toString
	public String toString() {
		String s = "key: " + key + " ";
		for (Decomp d : decomp) {
			s += d.toString();
		}
		return s;
	}
}
