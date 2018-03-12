/*
 * Copyright (c) 2007-2018 Siemens AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package com.siemens.ct.exi.core.datatype;

import java.io.IOException;

import com.siemens.ct.exi.core.context.QNameContext;
import com.siemens.ct.exi.core.datatype.strings.StringDecoder;
import com.siemens.ct.exi.core.datatype.strings.StringEncoder;
import com.siemens.ct.exi.core.io.channel.DecoderChannel;
import com.siemens.ct.exi.core.io.channel.EncoderChannel;
import com.siemens.ct.exi.core.types.BuiltInType;
import com.siemens.ct.exi.core.values.FloatValue;
import com.siemens.ct.exi.core.values.Value;

/**
 * 
 * @author Daniel.Peintner.EXT@siemens.com
 * @author Joerg.Heuer@siemens.com
 * 
 * @version 1.0.0-SNAPSHOT
 */

public class FloatDatatype extends AbstractDatatype {

	protected FloatValue lastValidFloat;

	public FloatDatatype(QNameContext schemaType) {
		super(BuiltInType.FLOAT, schemaType);
	}
	
	public DatatypeID getDatatypeID() {
		return DatatypeID.exi_double;
	}

	protected boolean isValidString(String value) {
		lastValidFloat = FloatValue.parse(value);
		return (lastValidFloat != null);
	}

	public boolean isValid(Value value) {
		if (value instanceof FloatValue) {
			lastValidFloat = ((FloatValue) value);
			return true;
		} else {
			return isValidString(value.toString());
		}
	}

	public void writeValue(QNameContext qnContext, EncoderChannel valueChannel,
			StringEncoder stringEncoder) throws IOException {
		valueChannel.encodeFloat(lastValidFloat);
	}

//	public Value readValue(QNameContext qnContext, DecoderChannel valueChannel,
//			StringDecoder stringDecoder) throws IOException {
//		return valueChannel.decodeFloatValue();
//	}
}