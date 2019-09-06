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



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;



/**
 * This class contains statics methods that can be used to encode different types of variables
 * @author <a href="https://github.com/VMormoris">Vasileios Mormoris </a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Encode {
	/**
	 * Method used to encode a String into the specified encoding
	 * @param data String to be encoded
	 * @param encoding The encoding that will be used for the String
	 * @return A byte[] of the encoded String
	 * @throws EmptyStringException Is thrown when trying to encode an empty String
	 */
	public static byte[] string(String data, Charset encoding) throws EmptyStringException {
		if(data.equals("")) throw new EmptyStringException();
		return data.getBytes(encoding);
	}
	
	/**
	 * Method used to encode a number (integer)
	 * @param data The number to be encoded
	 * @param type The bytes used to represent the number<br>
	 * 		 <b>- SHORT:</b> 2 Bytes (16 bit)<br>
	 * 		 <b>- INT:</b> 4 Bytes (32 bit)<br>
	 * 		 <b>- LONG:</b> 8 Bytes (64 bit)<br>
	 * @return A byte[] of the encoded number
	 * @throws ByteArrayOverflowException Is thrown when the number can't be represented by the specified type
	 * @see integer
	 */
	public static byte[] integer(long data,integer type) throws ByteArrayOverflowException {
		byte[] returnData=null;
		switch(type) {
			case SHORT:
				if(data<Short.MIN_VALUE||data>Short.MAX_VALUE) throw new ByteArrayOverflowException(type);
				returnData=ByteBuffer.allocate(2).putShort((short)data).array();
				break;
			case INT:
				if(data<Integer.MIN_VALUE||data>Integer.MAX_VALUE) throw new ByteArrayOverflowException(type);
				returnData=ByteBuffer.allocate(4).putInt((int)data).array();
				break;
			case LONG:
				returnData=ByteBuffer.allocate(8).putLong(data).array();
				break;
		}
		return returnData;
	}

	/**
	 * Method used to encode a number (integer)
	 * @param data The number to be encoded
	 * @param type The bytes used to represent the number<br>
	 * 		<b>- SHORT:</b> 2 Bytes (16 bit)<br>
	 * 	 	<b>- INT:</b> 4 Bytes (32 bit)<br>
	 * 	 	<b>- LONG:</b> 8 Bytes (64 bit)<br>
	 * @return A byte[] of the encoded number
	 * @throws ByteArrayOverflowException Is thrown when the number can't be represented by the specified type
	 * @see integer
	 */
	public static byte[] integer(int data, integer type) throws ByteArrayOverflowException {
		byte[] returnData=null;
		switch (type){
			case SHORT:
				if(data<Short.MIN_VALUE||data>Short.MAX_VALUE) throw new ByteArrayOverflowException(type);
				returnData=ByteBuffer.allocate(2).putShort((short)data).array();
				break;
			case INT:
				returnData=ByteBuffer.allocate(4).putInt(data).array();
				break;
			case LONG:
				returnData=ByteBuffer.allocate(8).putLong((long)data).array();
				break;
		}
		return returnData;
	}
	
	/**
	 * Method used for encoding number (integer) 2 byte format (16 bits)
	 * @param data The number to be encoded
	 * @return A byte[] of the encoded number
	 */
	public static byte[] Short(short data) {
		return (ByteBuffer.allocate(2).putShort(data).array());
	}
	
	/**
	 * Method used for encoding number (integer) 4 byte format (32 bits)
	 * @param data The number to be encoded
	 * @return A byte[] of the encoded number
	 */
	public static byte[] Int(int data) {
		return (ByteBuffer.allocate(4).putInt(data).array());
	}
	
	/**
	 * Method used for encoding number (integer) 8 byte format (64 bits) 
	 * @param data The number to be encoded
	 * @return A byte[] of the encoded number
	 */
	public static byte[] Long(long data) {
		return (ByteBuffer.allocate(8).putLong(data).array());
	}
	
	/**
	 * Method used for encoding a Series of numbers (integers) 8 byte format (64 bits) for each number
	 * @param data The numbers to be encoded
	 * @return A byte[] of the encoded numbers
	 */
	public static byte[] Longs(long[]data) {
		if(data.length==0) return null;
		ByteBuffer buffer=ByteBuffer.allocate(8*data.length);
		for(int i=0;i<data.length;i++) {
			int offset=i*8;
			buffer.putLong(offset, data[i]);
		}
		return buffer.array();
	}
	
	/**
	 * Method used for encoding a Series of numbers (integers) 4 byte format (32 bits) for each number
	 * @param data The numbers to be encoded
	 * @return A byte[] of the encoded numbers
	 */
	public static byte[] Ints(int[] data) {
		if(data.length==0) return null;
		ByteBuffer buffer=ByteBuffer.allocate(4*data.length);
		for(int i=0;i<data.length;i++) {
			int offset=i*4;
			buffer.putInt(offset, data[i]);
		}
		return buffer.array();
	}
	
	/**
	 * Method used for encoding a Series of numbers (integers) 2 byte format (16 bits) for each number
	 * @param data The numbers to be encoded
	 * @return A byte[] of the encoded numbers
	 */
	public static byte[] Shorts(short[]data) {
		if(data.length==0) return null;
		ByteBuffer buffer=ByteBuffer.allocate(2*data.length);
		for(int i=0;i<data.length;i++) {
			int offset=i*2;
			buffer.putShort(offset, data[i]);
		}
		return buffer.array();
	}
	
	
	/**
	 * Method used to encode a Series of numbers (integers) 
	 * @param data The numbers to be encoded
	 * @param type The bytes used to represent the numbers<br>
	 * 		 <b>- SHORT:</b> 2 Bytes (16 bit)<br>
	 * 		 <b>- INT:</b> 4 Bytes (32 bit)<br>
	 * 		 <b>- LONG:</b> 8 Bytes (64 bit)<br>
	 * @return A byte[] of the encoded numbers
	 * @throws ByteArrayOverflowException Is thrown when the number can't be represented by the specified type
	 * @see integer
	 */
	public static byte[] integers(long[]data,integer type) throws ByteArrayOverflowException {
		if(data.length==0) return null;
		ByteBuffer buffer;
		switch(type) {
		case SHORT:
			buffer=ByteBuffer.allocate(data.length*2);
			for(int i=0;i<data.length;i++) {
				int offset=i*2;
				if(data[i]<Short.MIN_VALUE||data[i]>Short.MAX_VALUE) throw new ByteArrayOverflowException(type);
				buffer.putShort(offset, (short)data[i]);
			}
			break;
		case LONG: 
			buffer=ByteBuffer.allocate(data.length*8);
			for(int i=0;i<data.length;i++) {
				int offset=i*8;
				buffer.putLong(offset, data[i]);
			}
			break;
		case INT:
			buffer=ByteBuffer.allocate(data.length*4);
			for(int i=0;i<data.length;i++) {
				int offset=i*4;
				if(data[i]<Integer.MIN_VALUE||data[i]>Integer.MAX_VALUE) throw new ByteArrayOverflowException(type);
				buffer.putInt(offset, (int)data[i]);
			}
			break;
		default:
			return null;
		}
		return buffer.array();
	}

	/**
	 * Method used to encode a Series of numbers (integers)
	 * @param data The numbers to be encoded
	 * @param type The bytes used to represent the numbers<br>
	 * 	 	<b>- SHORT:</b> 2 Bytes (16 bit)<br>
	 * 	 	<b>- INT:</b> 4 Bytes (32 bit)<br>
	 * 	 	<b>- LONG:</b> 8 Bytes (64 bit)<br>
	 * @return A byte[] of the encoded numbers
	 * @throws ByteArrayOverflowException Is thrown when the number can't be represented by the specified type
	 * @see integer
	 */
	public static byte[] integers(int[] data, integer type) throws ByteArrayOverflowException {
		if(data.length==0) return null;
		ByteBuffer buffer;
		switch (type){
			case SHORT:
				buffer=ByteBuffer.allocate(2*data.length);
				for(int i=0;i<data.length;i++){
					int offset=i*2;
					if(data[i]<Short.MIN_VALUE||data[i]>Short.MAX_VALUE) throw new ByteArrayOverflowException(type);
					buffer.putShort(offset, (short)data[i]);
				}
				break;
			case INT:
				buffer=ByteBuffer.allocate(4*data.length);
				for(int i=0;i<data.length;i++){
					int offset=i*4;
					buffer.putInt(offset, data[i]);
				}
				break;
			case LONG:
				buffer=ByteBuffer.allocate(8*data.length);
				for(int i=0;i<data.length;i++){
					int offset=i*8;
					buffer.putLong(offset, (long)data[i]);
				}
				break;
			default:
				return null;
		}
		return buffer.array();
	}
	
	/**
	 * Method used for encoding an Object that implements the Serializable interface
	 * @param data The Object to be encoded
	 * @return A byte[] representing the encoded Object 
	 */
	public static byte[] object(Serializable data) {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos;
		byte[] returnData=null;
		try {
			oos=new ObjectOutputStream(bos);
			oos.writeObject(data);
			oos.flush();
			oos.close();
			returnData=bos.toByteArray();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnData;
	}
	
	/**
	 * Method used for encoding a Series Objects that implements the Serializable interface
	 * @param data The Objects to be encoded
	 * @return A byte[] representing the encoded Objects 
	 */
	public static byte[] objects(Serializable[] data) {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos;
		byte[] returnData=null;
		try {
			oos=new ObjectOutputStream(bos);
			oos.writeInt(data.length);
			oos.flush();
			for(Serializable obj: data) {
				oos.writeObject(obj);
				oos.flush();
			}
			oos.close();
			returnData=bos.toByteArray();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnData;
	}
	
	/**
	 * Method used for encoding a number (decimal) 4 byte format (32 bits)
	 * @param data The number to be encoded
	 * @return A byte[] representing the encoded number
	 */
	public static byte[] Float(float data) {
		return ByteBuffer.allocate(4).putFloat(data).array();
	}
	
	/**
	 * Method used for encoding a number (decimal) 8 byte format (64 bits)
	 * @param data The number to be encoded
	 * @return A byte[] representing the encoded number
	 */
	public static byte[] Double(double data) {
		return ByteBuffer.allocate(8).putDouble(data).array();
	}
	
	/**
	 * Method used for encoding a numbers (decimals) 4 byte format (32 bits) for each number
	 * @param data The numbers  to be encoded
	 * @return A byte[] representing the encoded numbers
	 */
	public static byte[] Floats(float[] data) {
		if(data.length==0) return null;
		ByteBuffer buffer=ByteBuffer.allocate(4*data.length);
		for(int i=0;i<data.length;i++) {
			int offset=i*4;
			buffer.putFloat(offset,data[i]);
		}
		return buffer.array();
	}
	
	/**
	 * Method used for encoding a numbers (decimals) 8 byte format (64 bits) for each number
	 * @param data The numbers to be encoded
	 * @return A byte[] representing the encoded numbers
	 */
	public static byte[] Doubles(double[] data) {
		if(data.length==0) return null;
		ByteBuffer buffer=ByteBuffer.allocate(8*data.length);
		for(int i=0;i<data.length;i++) {
			int offset=i*8;
			buffer.putDouble(offset, data[i]);
		}
		return buffer.array();
	}

}