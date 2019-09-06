/*
*
*	This file is part of EncDec.
*
*	Copyright (c) 2019 Mormoris Vasileios
*
*	Permission is hereby granted, free of charge, to any person obtaining a copy of
*	this software and associated documentation files (the "Software"), to deal in
*	the Software without restriction, including without limitation the rights to
*	use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
*	the Software, and to permit persons to whom the Software is furnished to do so,
*	subject to the following conditions:
*
*	The above copyright notice and this permission notice shall be included in all
*	copies or substantial portions of the Software.
*
*	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
*	FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
*	COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
*	IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
*	CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*
**/
package encdec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


/**
 * This class contains statics methods that can be used to decode different types of variables
 * @author <a href="https://github.com/VMormoris">Vasileios Mormoris </a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Decode {
	/**
	 * Method used to decode a String using the specified format
	 * @param data A byte[] of the data to be decoded
	 * @param encoding The format witch the String has been encoded  
	 * @return The String represented by the byte[]
	 */
	public static String string(byte[]data,Charset encoding) {
		if (data.length==0) return null;
		return new String(data, encoding);
	}
	
	/**
	 * Method used for decoding a number (integer) in 2 byte format (16 bits)
	 * @param data byte[] of the encoded number
	 * @return The number represented by the byte[]
	 * @throws DecodeException Is thrown if you don't try to decode 2 bytes
	 */
	public static short Short(byte[] data) throws DecodeException {
		if(data.length!=2) throw new DecodeException(integer.SHORT);
		return ByteBuffer.wrap(data).getShort();
	}
	
	/**
	 * Method used for decoding a number (integer) in 4 byte format (32 bits)
	 * @param data byte[] of the encoded number
	 * @return	The number represented by the byte[]
	 * @throws DecodeException Is thrown if you don't try to decode 4 bytes
	 */
	public static int Int(byte[] data) throws DecodeException {
		if(data.length!=4) throw new DecodeException(integer.INT);
		return ByteBuffer.wrap(data).getInt();
	}

	/**
	 * Method used for decoding a number (integer) in 8 byte format (64 bits)
	 * @param data byte[] of the encoded number
	 * @return	The number represented by the byte[]
	 * @throws DecodeException Is thrown if you don't try to decode 8 bytes
	 */
	public static long Long(byte[] data) throws DecodeException {
		if(data.length!=8) throw new DecodeException(integer.LONG);
		return ByteBuffer.wrap(data).getLong();
	}

	/**
	 * Method used for decoding a Series of numbers (integers) in 2 byte format (16 bits)
	 * @param data byte[] of the encoded numbers
	 * @return A short[] holding the numbers
	 * @throws DecodeException Is thrown if you don't try to decode a multiple of 2 bytes
	 */
	public static short[] Shorts(byte[] data) throws DecodeException {
		if(data.length%2!=0) throw new DecodeException(integer.SHORT, true);
		ByteBuffer buffer=ByteBuffer.wrap(data);
		short[] returnData=new short[data.length/2];
		for(int i=0;i<returnData.length;i++) {
			int offset=2*i;
			returnData[i]=buffer.getShort(offset);
		}
		return returnData;
	}

	/**
	 * Method used for decoding a Series of numbers (integers) in 4 byte format (32 bits)
	 * @param data byte[] of the encoded numbers
	 * @return	A int[] holding the numbers
	 * @throws DecodeException Is thrown if you don't try to decode a multiple of 4 bytes
	 */
	public static int[] Ints(byte[] data) throws DecodeException {
		if(data.length%4!=0) throw new DecodeException(integer.INT, true);
		ByteBuffer buffer=ByteBuffer.wrap(data);
		int[] returnData=new int[data.length/4];
		for(int i=0;i<returnData.length;i++) {
			int offset=i*4;
			returnData[i]=buffer.getInt(offset);
		}
		return returnData;
	}

	/**
	 * Method used for decoding a Series of numbers (integers) in 8 byte format (64 bits)
	 * @param data byte[] of the encoded numbers
	 * @return	A long[] holding the numbers
	 * @throws DecodeException Is thrown if you don't try to decode a multiple of 8 bytes
	 */
	public static long[] Longs(byte[] data) throws DecodeException {
		if(data.length%8!=0) throw new DecodeException(integer.LONG, true);
		ByteBuffer buffer=ByteBuffer.wrap(data);
		long[] returnData=new long[data.length/8];
		for(int i=0;i<returnData.length;i++) {
			int offset=i*8;
			returnData[i]=buffer.getLong(offset);
		}
		return returnData;
	}

	/**
	 * Method used for decoding an Object that implements the Serializable interface
	 * @param data byte[] representing the encoded Object
	 * @return An Object represented by the byte[]
	 */
	public static Serializable object(byte[] data) {
		ByteArrayInputStream bis=new ByteArrayInputStream(data);
		ObjectInputStream ois;
		Serializable obj=null;
		try {
			ois=new ObjectInputStream(bis);
			obj=(Serializable) ois.readObject();
			ois.close();
			bis.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * Method used for decoding a Series of Objects that implements the Serializable interface
	 * @param data The byte[] representing the encoded Objects
	 * @return A Serializable[] containing the Objects
	 */
	public static Serializable[] objects(byte[] data) {
		ByteArrayInputStream bis=new ByteArrayInputStream(data);
		ObjectInputStream ois;
		Serializable[] objects=null;
		try {
			ois=new ObjectInputStream(bis);
			objects=new Serializable[ois.readInt()];
			for(int i=0;i<objects.length;i++) {
				objects[i]=(Serializable)ois.readObject();
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return objects;
	}

	/**
	 * Method used for decoding a number (decimal) in 4 byte format (32 bits)
	 * @param data The byte[] representing the encoded number
	 * @return	The number represented by the byte[]
	 * @throws DecodeException Is thrown if you don't try to decode 4 bytes
	 */
	public static float Float(byte[] data) throws DecodeException {
		if(data.length!=4) throw new DecodeException(decimal.FLOAT);
		return ByteBuffer.wrap(data).getFloat();
	}

	/**
	 * Method used for decoding a number (decimal) in 8 byte format (64 bits)
	 * @param data The byte[] representing the encoded number
	 * @return	The number represented by the byte[]
	 * @throws DecodeException Is thrown if you don't try to decode 8 bytes
	 */
	public static double Double(byte[] data) throws DecodeException {
		if(data.length!=8) throw new DecodeException(decimal.DOUBLE);
		return ByteBuffer.wrap(data).getDouble();
	}

	/**
	 * Method used for decoding a Series of numbers (decimals) in 4 byte format (32 bits)
	 * @param data The byte[] representing the encoded numbers
	 * @return	A float[] holding the numbers
	 * @throws DecodeException Is thrown if you don't try to decode a multiple of 4 bytes
	 */
	public static float[] Floats(byte[] data) throws DecodeException {
		if(data.length%4!=0) throw new DecodeException(decimal.FLOAT, true);
		ByteBuffer buffer=ByteBuffer.wrap(data);
		float[] returnData=new float[data.length/4];
		for (int i=0;i<returnData.length;i++){
			int index=i*4;
			returnData[i]=buffer.getFloat(index);
		}
		return returnData;
	}

	/**
	 * Method used for decoding a Series of numbers (decimals) in 8 byte format (64 bits)
	 * @param data The byte[] representing the encoded numbers
	 * @return	A double[] holding the numbers
	 * @throws DecodeException Is thrown if you don't try to decode a multiple of 8 bytes
	 */
	public static double[] Doubles(byte[] data) throws DecodeException {
		if(data.length%8!=0)throw new DecodeException(decimal.DOUBLE, true);
		ByteBuffer buffer=ByteBuffer.wrap(data);
		double[] returnData=new double[data.length/8];
		for(int i=0;i<returnData.length;i++){
			int index=i*8;
			returnData[i]=buffer.getDouble(index);
		}
		return returnData;
	}
}
