package mikera.vectorz.impl;

import mikera.indexz.Index;
import mikera.vectorz.AScalar;
import mikera.vectorz.AVector;
import mikera.vectorz.Vector1;
import mikera.vectorz.Vectorz;
import mikera.vectorz.util.ErrorMessages;
import mikera.vectorz.util.VectorzException;

/**
 * A sparse mutable vector that has a only one element that can be non-zero.
 * All other components are forced to remain at zero, setting them to a non-zero value results in an exception.
 * @author Mike
 *
 */
@SuppressWarnings("serial")
public final class SingleElementVector extends ASparseVector {
	private final int index;
	private double value;
	
	public SingleElementVector(int componentIndex, int dimensions) {
		this(componentIndex,dimensions,0.0);
	}
	
	public SingleElementVector(int componentIndex, int dimensions, double value) {
		super(dimensions);
		
		if (dimensions<=0) throw new IllegalArgumentException("SingleElementVEctor must have >= 1 dimensions");
		if (componentIndex<0||componentIndex>=dimensions) throw new IllegalArgumentException("Invalid non-zero component index: "+componentIndex);
		
		this.index=componentIndex;
		this.value=value;
	}
	
	public static AVector create(double val, int i, int len) {
		return new SingleElementVector(i,len,val);
	}

	@Override
	public double magnitude() {
		return value;
	}
	
	@Override
	public double elementSum() {
		return value;
	}
	
	@Override
	public double elementMax(){
		return (length>1)?Math.max(0.0, value):value;
	}
	
	@Override
	public double elementMin(){
		return (length>1)?Math.min(0.0, value):value;
	}
	
	@Override
	public double magnitudeSquared() {
		return value*value;
	}
	
	@Override 
	public void square() {
		value=value*value;
	}
	
	@Override
	public double normalise() {
		double ret=value;
		if (value>0) {
			value=1.0;
		} else if (value<0) {
			value=-1.0;
		} 
		return ret;
	}
	
	@Override
	public boolean isFullyMutable() {
		return (length<=1);
	}
	
	@Override
	public boolean isElementConstrained() {
		return (length>1);
	}
	
	@Override
	public void multiply(double factor) {
		value*=factor;
	}
	
	@Override
	public double density() {
		return 1.0/length();
	}

	@Override
	public double get(int i) {
		if(!((i>=0)&&(i<length))) throw new IndexOutOfBoundsException();
		return (i==index)?value:0.0;
	}
	

	@Override
	public double unsafeGet(int i) {
		return (i==index)?value:0.0;
	}

	@Override
	public void set(int i, double value) {
		if(!((i>=0)&&(i<length))) throw new IndexOutOfBoundsException();
		if (i==index) {
			this.value=value;
		} else { 
			if (value!=0.0) throw new VectorzException("SingleElementVector not mutable at position: "+i);
		}
	}
	
	@Override
	public final AScalar slice(int i) {
		if (i==index) return VectorIndexScalar.wrap(this, i);
		if ((i<0)||(i>=length)) throw new IndexOutOfBoundsException(ErrorMessages.invalidIndex(this, i));
		return ImmutableScalar.ZERO;
	}
	
	@Override
	public AVector subVector(int offset, int length) {
		int end=offset+length;
		if ((offset>index)||(end<=index)) {
			if ((offset<0)||(end>this.length())) throw new IndexOutOfBoundsException(ErrorMessages.invalidRange(this, offset, length));
			return Vectorz.createZeroVector(length);
		}
		return super.subVector(offset, length);
	}
	
	@Override
	public SingleElementVector exactClone() {
		return new SingleElementVector(index,length,value);
	}

	@Override
	public int nonSparseElementCount() {
		return 1;
	}

	@Override
	public AVector nonSparseValues() {
		return Vector1.of(value);
	}

	@Override
	public Index nonSparseIndexes() {
		return Index.of(index);
	}

	@Override
	public boolean includesIndex(int i) {
		return (i==index);
	}

}
