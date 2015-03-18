package org.scribe.extractors;

import static org.junit.Assert.*;

import org.junit.*;
import org.scribe.exceptions.*;
import org.scribe.model.*;

public class PassportTokenExtractorTest
{

  private TokenExtractor20Impl extractor;

  @Before
  public void setup()
  {
    extractor = new TokenExtractor20Impl();
  }

  @Test
  public void shouldExtractTokenFromOAuthStandardResponse()
  {
    String response = "{\"access_token\":\"eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOlsicGFzc3BvcnQiXSwiZXhwIjoxNDI2NjM4NzA0LCJ1c2VyX25hbWUiOiJqb2huLmFsYWRlIiwianRpIjoiNTdjNjNlNGYtY2Q3Zi00OTExLWFiZTAtN2JiMTIyZWJhMWIwIiwiY2xpZW50X2lkIjoic21hcnRpbnN1cmUiLCJzY29wZSI6WyJwcm9maWxlIl19.ZdaMSawI-WNk4EZwd4mAFEIAXcFhpPXd_Tx_Fy55ikxnwNwI725HwSVmI27ALn6BGdPcqvh4Q3RfIm3PmXt13dSplcId6l8mQOBZ4HD4o0MwuXCqVI1BauquQkkEfs9QVK8vpoPBSabFFxHs2jBwMkI4ZYIOG8OSAIzXuxMj0RPIqIvKLC1VhZAxmvSuVzFGFU0Dl6jd5sEaMEUuYOvZxNTRwEUqOLVG7l9pobNhyy5gXlFoTOXiZnwHdBGDTMBelXnPFwlDuuhwSZDLQ-L5FVlOfwFpO7PEpHnyoaGN_XqxYfXRsjndZ0Du3TBDIHWbanAkCJBGQ_vbBxOrMDOYdg\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOlsicGFzc3BvcnQiXSwidXNlcl9uYW1lIjoiam9obi5hbGFkZSIsInNjb3BlIjpbInByb2ZpbGUiXSwiYXRpIjoiNTdjNjNlNGYtY2Q3Zi00OTExLWFiZTAtN2JiMTIyZWJhMWIwIiwiZXhwIjoxNDI5MTg3NTA0LCJqdGkiOiIxZDE5ZTkyMS01MzY3LTRhNWItYjYyMy1lMTMwYjI4MmE2M2UiLCJjbGllbnRfaWQiOiJzbWFydGluc3VyZSJ9.XRlXERrNar6yVU_EL3cZAbj63bq1JxGvnYA7A64wSFA2i5NtqSySlsnooX8glNQNBdBZEWK-95IzyQOimguMpURyFhL14yoNSxX4xAx_ZqX4JvJYM3NdVW-EgMq-DUjC3aO-dQ9xIlx9qbv1BNUhbZfM776h7VnyeQmUyzM3_b2iwVK614F1OnpcsyssCeIegp8i3eUrxBbYe7qytSKip2AgKn7jb_u7p7JE963xPjr1lqBN5GpTOzPVG5hz9aL5tVh2-k1afzJ2NcE3j-fFcr0vs0aC4WHwD7ah-4F4FwBJq_Sbi5UshDzX_wZ6B46ati74ivk11NDaZKqDWEazLw\",\"expires_in\":43199,\"scope\":\"profile\",\"jti\":\"57c63e4f-cd7f-4911-abe0-7bb122eba1b0\"}";
    Token extracted = extractor.extract(response);
    assertEquals("eyJhdWQiOlsicGFzc3BvcnQiXSwiZXhwIjoxNDI2NjM4NzA0LCJ1c2VyX25hbWUiOiJqb2huLmFsYWRlIiwianRpIjoiNTdjNjNlNGYtY2Q3Zi00OTExLWFiZTAtN2JiMTIyZWJhMWIwIiwiY2xpZW50X2lkIjoic21hcnRpbnN1cmUiLCJzY29wZSI6WyJwcm9maWxlIl19", extracted.getToken());
    assertEquals("", extracted.getSecret());
  }
/*
  @Test
  public void shouldExtractTokenFromResponseWithExpiresParam()
  {
    String response = "access_token=166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE&expires=5108";
    Token extracted = extractor.extract(response);
    assertEquals("166942940015970|2.2ltzWXYNDjCtg5ZDVVJJeg__.3600.1295816400-548517159|RsXNdKrpxg8L6QNLWcs2TVTmcaE", extracted.getToken());
    assertEquals("", extracted.getSecret());
  }

  @Test
  public void shouldExtractTokenFromResponseWithManyParameters()
  {
    String response = "access_token=foo1234&other_stuff=yeah_we_have_this_too&number=42";
    Token extracted = extractor.extract(response);
    assertEquals("foo1234", extracted.getToken());
    assertEquals("", extracted.getSecret());
  }

  @Test(expected = OAuthException.class)
  public void shouldThrowExceptionIfTokenIsAbsent()
  {
    String response = "&expires=5108";
    extractor.extract(response);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfResponseIsNull()
  {
    String response = null;
    extractor.extract(response);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfResponseIsEmptyString()
  {
    String response = "";
    extractor.extract(response);
  }

  */
}
