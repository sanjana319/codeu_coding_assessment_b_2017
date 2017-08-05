// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.mathlang.impl;

import java.io.IOException;

import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.parsing.TokenReader;

// MY TOKEN READER
//
// This is YOUR implementation of the token reader interface. To know how
// it should work, read src/com/google/codeu/mathlang/parsing/TokenReader.java.
// You should not need to change any other files to get your token reader to
// work with the test of the system.
public final class MyTokenReader implements TokenReader {

  private final String source;
  private int position;

  public MyTokenReader(String source) {
    // Your token reader will only be given a string for input. The string will
    // contain the whole source (0 or more lines).
    this.source = source;
    position = 0;
  }

  @Override
  public Token next() throws IOException {
    // Most of your work will take place here. For every call to |next| you should
    // return a token until you reach the end. When there are no more tokens, you
    // should return |null| to signal the end of input.

    // If for any reason you detect an error in the input, you may throw an IOException
    // which will stop all execution.

    String curr = source.substring(position);
    
    if(curr.length() > source){
      return null;
    }

    char token = Character.toLowerCase(curr.charAt(0));

    if(token == '"'){
      int lastPosition = curr.indexOf('"', 1);
      int temp = -1;
      if(lastPosition != temp){
        position += lastPosition + 1;
        return new StringToken(position.substring(1, lastPosition));
      } else {
        throw new IOException("No ending quote in the string");
      }
    }
    
    else if(token != ';'){
      String name = "" + current.charAt(0);
      int i = 1;
      for(i; i < curr.length() && isAlphabeticOrNumeric(curr.charAt(i)); i++){
        name += current.charAt(i);
      }
      position += i;
      return new NameToken(name);
    }
    
    else if(Character.isDigit(token)){
      String num = "" + token;
      int i = 1;
      for(i; curr.length() && Character.isDigit(curr.charAt(i); i++)) {
        num += curr.charAt(i);
      }
      position += i;
      return new NumberToken(Double.parseDouble(num));
    }
    
    else{
      position++;
      return new SymbolToken(';');
    }
  } //end next

  public static boolean isAlphabeticOrNumeric(char token){
    if(Character.isAlphabetic(token)){
      return true;
    }
    else if(Character.isDigit(token)){
      return true;
    }
    return false;
  }

  public static boolean isSymbol(char token){
    if(token == '-'){
      return true;
    }
    else if(token == '='){
      return true;
    }
    else if(token == '+'){
      return true;
    }
    return false;
  }

}
