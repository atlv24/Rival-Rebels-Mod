/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 *
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 *
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.common.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;

import org.apache.commons.io.IOUtils;

import scala.actors.threadpool.Arrays;

public class FileRW
{
	public static String read(File file)
	{
		StringBuilder text = new StringBuilder();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				text.append(line);
				text.append("\n");
			}
		}
		catch (Exception e) {}
		finally
		{
			try
			{
				if (reader != null)
				{
					reader.close();
				}
			}
			catch (IOException e) {}
		}
		return text.toString();
	}
	
	public static byte[] readbytes(File file)
	{
		FileInputStream fis = null;
		byte[] data = new byte[0];
		try
		{
			fis = new FileInputStream(file);
			data = IOUtils.toByteArray(fis);
		}
		catch (Exception e) {}
		finally
		{
			try
			{
				if (fis != null) fis.close();
			}
			catch (IOException e) {}
		}
		return data;
	}
	
	public static void write(File file, String text)
	{
		Writer output = null;
		try
		{
			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
		}
		catch (IOException e) {}
		finally
		{
			try
			{
				if (output != null) output.close();
			}
			catch (IOException e) {}
		}
	}
	
	public static void writebytes(File file, byte[] data)
	{
		FileOutputStream fos = null;
		try
		{
			file.delete();
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(data);
		}
		catch (Exception e) {}
		finally
		{
			try
			{
				if (fos!=null) fos.close();
			}
			catch (IOException e) {}
		}
	}
	
	public static boolean goToURL(String string)
	{
		try
		{
			Class oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
			oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(string) });
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static byte[] getBytesString(String str)
	{
		char[] buffer = str.toCharArray();
		byte[] bytes = new byte[buffer.length];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) buffer[i];
		}
		return bytes;
	}
	
	public static String getStringBytes(byte[] bytes)
	{
		char[] buffer = new char[bytes.length];
		for (int i = 0; i < bytes.length; i++)
		{
			buffer[i] = (char) bytes[i];
			if (buffer[i] == 'ﾧ') buffer[i] = '§';
		}
		
		return new String(buffer);
	}
}
