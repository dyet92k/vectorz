package mikera.vectorz.impl;

import mikera.vectorz.AVector;

/**
 * Derived vector delegates all calls to an underlying vector
 * @author Mike
 */
public abstract class ADerivedVector extends AVector {
	private static final long serialVersionUID = -9039112666567131812L;

	protected final AVector source;
	
	protected ADerivedVector(AVector source) {
		this.source=source;
	}
	
	@Override
	public int length() {
		return source.length();
	}

	@Override
	public double get(int i) {
		return source.get(i);
	}
	
	@Override
	public double unsafeGet(int i) {
		return source.unsafeGet(i);
	}
	
	@Override 
	public double dotProduct(double[] data, int offset) {
		return source.dotProduct(data,offset);
	}

	@Override
	public void set(int i, double value) {
		source.set(i,value);
	}
	
	@Override
	public void unsafeSet(int i, double value) {
		source.unsafeSet(i,value);
	}
	
	@Override
	public void getElements(double[] dest, int offset) {
		source.getElements(dest, offset);
	}
	
	@Override
	public boolean isView() {
		return true;
	}
	
	@Override
	public boolean isFullyMutable() {
		return source.isFullyMutable();
	}
	
	@Override
	public boolean isMutable() {
		return source.isMutable();
	}

}
