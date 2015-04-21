package org.scribe.extractors;

import java.util.regex.*;

import org.scribe.exceptions.*;
import org.scribe.model.*;
import org.scribe.utils.*;

/**
 * Default implementation of {@link AccessTokenExtractor}. Conforms to OAuth 2.0
 */
public class TokenExtractor20Impl implements AccessTokenExtractor
{
  private static final String TOKEN_REGEX = "access_token=([^&]+)";
  private static final String PASSPORT_TOKEN_REGEX = "access_token([^,]*)";
  private static final String EMPTY_SECRET = "";

  /**
   * {@inheritDoc} 
   */
  public Token extract(String response)
  {
    //Please note that I hacked this function to allow decoding of response from passport(interswitchng Passport Provider)
    //The default mather should be used first(important cos the regex for passport matches default so default matching should come first), 
    //then if it is incorrect, Passport match is used, after which it can then throw exception
    Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");

    Matcher passportMatcher = Pattern.compile(PASSPORT_TOKEN_REGEX).matcher(response);
    Matcher defaultMatcher = Pattern.compile(TOKEN_REGEX).matcher(response);
    
    if(defaultMatcher.find()){
      System.out.println("Default..........");
      String userToken = OAuthEncoder.decode(defaultMatcher.group(1));
      return new Token(userToken, EMPTY_SECRET, response);
    }
    else if (passportMatcher.find())
    {
      System.out.println("Passport..........");
      String token = passportMatcher.group(0).replaceAll("access_token\":", "");
      String userToken = token.split("\\.")[1];
      return new Token(userToken, EMPTY_SECRET, response);
    } 
    else{
      throw new OAuthException("Response body is incorrect. Can't extract a token from this: '" + response + "'", null);
    }
  }
}
