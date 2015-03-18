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
    Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");

    Matcher matcher = Pattern.compile(PASSPORT_TOKEN_REGEX).matcher(response);
    if (matcher.find())
    {
      String token = matcher.group(0).replaceAll("access_token\":", "");
      String userToken = token.split("\\.")[1];
      return new Token(userToken, EMPTY_SECRET, response);
    } 
    else
    {
      throw new OAuthException("Response body is incorrect. Can't extract a token from this: '" + response + "'", null);
    }
  }
}
