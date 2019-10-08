package com.nouni.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Main {

	public static void main(String[] args) throws Exception {
		BigByteArray arr = null;
		try {
			arr = new BigByteArray(Integer.MAX_VALUE * 2);
			System.out.println(arr.getSize());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(arr != null) arr.free();
		}
	}
	
	static Unsafe getUnsafe() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		return (Unsafe)f.get(null);
		
	}
	
	static class BigByteArray {
		final static int CELL_SIZE_BYTE = 1;
		protected long size;
		protected long address;
		protected Unsafe unsafe;
		
		public BigByteArray(long size) throws Exception {
			unsafe = getUnsafe();
			this.size = size;
			this.address = unsafe.allocateMemory(size * CELL_SIZE_BYTE);//size bytes
		}
		
		public byte get(long idx) {
			return unsafe.getByte(address + idx * CELL_SIZE_BYTE);
		}
		
		public void set(long idx, byte value) {
			unsafe.putByte(address + idx * CELL_SIZE_BYTE, value);
		}
		
		public long getSize() {
			return size;
		}
		
		public void free() {
			unsafe.freeMemory(address);
		}
	}

	static class JNI {
		public native String getName();
	}
	
	static {
		
	}
}
