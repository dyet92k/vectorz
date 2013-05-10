package mikera.vectorz;

public final class Tools {
	public static void debugBreak(Object o) {
		o.toString();
	}
	
	/**
	 * Hashcode for an int, defined as the value of the int itself for consistency with java.lang.Integer
	 * 
	 * @param value
	 * @return
	 */
	public static int hashCode(int value) {
		return value;
	}
	
	
	/** 
	 * Hashcode for a double primitive
	 * 
	 * @param d
	 * @return
	 */
	public static int hashCode(double d) {
		return hashCode(Double.doubleToLongBits(d));
	}
	
	/**
	 * Hashcode for a long primitive
	 * @param l
	 * @return
	 */
	public static int hashCode(long l) {
		return (int) (l ^ (l >>> 32));
	}

	/**
	 * Converts an numerical object to a primitive double
	 * Handles numbers and scalars
	 */
	public static double toDouble(Object object) {
		if (object instanceof Double) {
			return (Double)object;
		} else if (object instanceof Number) {
			return ((Number)object).doubleValue();
		} else if (object instanceof AScalar) {
			return ((AScalar)object).get();
		} else {
			throw new IllegalArgumentException("Cannot convert to double: "+object.toString());
		}
	}
	
	/**
	 * Convenience overload to convert a Double to a primitive double
	 */
	public static double toDouble(Double d) {
		return d;
	}
	
	/**
	 * Convenience overload to convert a Number to a primitive double
	 */
	public static double toDouble(Number d) {
		return d.doubleValue();
	}

	/**
	 * Tests if two double values are approximately equal
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean epsilonEquals(double a, double b) {
		return epsilonEquals(a,b,Vectorz.TEST_EPSILON);
	}
	
	/**
	 * Tests if two double values are approximately equal,
	 * up to a given tolerance
     */ 
	public static boolean epsilonEquals(double a, double b,double tolerance) {
		double diff=a-b;
		if ((diff>tolerance)||(diff<-tolerance)) return false;
		return true;
	}

	public static int[] consArray(int a, int[] as) {
		int len=as.length;
		int[] nas=new int[len+1];
		nas[0]=a;
		System.arraycopy(as, 0, nas, 1, len);
		return nas;
	}

	public static void copyIntsToLongs(int[] src, long[] dst) {
		for (int i=0; i<src.length; i++) {
			dst[i]=src[i];
		}
	}

	public static long arrayProduct(int[] shape) {
		long r=1;
		for (int x:shape) {
			r*=x;
		}
		return r;
	}

	public static long arrayProduct(int[] shape, int from, int to) {
		long r=1;
		for (int i=from; i<to; i++) {
			r*=shape[i];
		}
		return r;
	}


}
