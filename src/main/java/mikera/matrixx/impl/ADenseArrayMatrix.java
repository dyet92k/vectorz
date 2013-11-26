package mikera.matrixx.impl;

import mikera.vectorz.util.DoubleArrays;

/**
 * Abstract base class for matrices wrapping  a dense (rows*cols) subset of a double[] array
 * @author Mike
 *
 */
public abstract class ADenseArrayMatrix extends AStridedMatrix {

	protected ADenseArrayMatrix(double[] data, int rows, int cols) {
		super(data, rows, cols);
	}

	@Override
	public abstract int getArrayOffset();
	
	@Override
	public boolean isPackedArray() {
		return (getArrayOffset()==0) && (data.length ==(rows*cols));
	}
	
	@Override
	public boolean isZero() {
		return DoubleArrays.isZero(data, getArrayOffset(), rows*cols);
	}
	
	@Override
	public int rowStride() {
		return cols;
	}
	
	@Override
	public int columnStride() {
		return 1;
	}
	
	@Override
	public double unsafeGet(int i, int j) {
		return data[index(i,j)];
	}
	
	@Override
	public void copyRowTo(int row, double[] dest, int destOffset) {
		System.arraycopy(data, row*cols, dest, destOffset, cols);
	}
	
	@Override
	public void unsafeSet(int i, int j,double value) {
		data[index(i,j)]=value;
	}
	
	protected int index(int row, int col) {
		return getArrayOffset()+(row*cols)+col;
	}

}
