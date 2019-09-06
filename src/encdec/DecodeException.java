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

/**
 * Exception thrown when bytes given for decode doesn't match bytes need it for the specific type.
 * @author <a href="https://github.com/VMormoris">Vasileios Mormoris </a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class DecodeException extends Exception {

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Info about the exception
	 */
	String msg="";
	
	/*package-private*/DecodeException(integer number) {
		this(number, false);
	}

	/*package-private*/DecodeException(decimal number) {
		this(number, false);
	}

	/*package-private*/DecodeException(integer number, boolean array){
		switch(number) {
			case SHORT:
				msg= (array ) ? "Decoding a SHORT array needs multiple of 2 bytes" : "Decoding a SHORT needs 2 bytes";
				break;
			case INT:
				msg= (array ) ? "Decoding a INT array needs multiple of 4 bytes" : "Decoding a INT needs 4 bytes";
				break;
			case LONG:
				msg= (array ) ? "Decoding a LONG array needs multiple of 8 bytes" : "Decoding a LONG needs 8 bytes";
				break;

		}
	}

	/*package-private*/DecodeException(decimal number, boolean array) {
		switch(number) {
			case FLOAT:
				msg= (array ) ? "Decoding a FLOAT array needs multiple of 4 bytes" : "Decoding a FLOAT needs 4 bytes";
				break;
			case DOUBLE:
				msg= (array ) ? "Decoding a DOUBLE array needs multiple of 8 bytes" : "Decoding a DOUBLE needs 8 bytes";
				break;

		}
	}
	
	@Override
	public String getMessage() {return msg;}

}
