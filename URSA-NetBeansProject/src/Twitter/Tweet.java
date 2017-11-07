package Twitter;

import dominio.Oportunidade;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author 152072
 */
public class Tweet {
        static String consumerKeyStr =       "ssSa9oGptlEWd5HZhMLv3fvAY";
	static String consumerSecretStr =    "zb9zKZsRYZyUry4GkDWXdA3H71RmMBb4gorfsU09iXouxEGRIG";
	static String accessTokenStr =       "925046275610947584-knr4EDD1Bd1JRtf5KashL6w5CYECL5C";
	static String accessTokenSecretStr = "4Yd2cBFx1uyjVYuCKNDZo7lqL22ziUXWTusKO2N738XxB";
        
        public void Tweet(Oportunidade op) {
            //op.getDescricao();
                    
            try {
                Twitter twitter = new TwitterFactory().getInstance();

                twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
                AccessToken accessToken = new AccessToken(accessTokenStr,
                        accessTokenSecretStr);

                twitter.setOAuthAccessToken(accessToken);
                
                String pretweet = "A nova oportunidade de cargo "+op.getCargo().getDescricao()+" está disponivel."+
                        "\n"+op.getDescricao();
                
                if (pretweet.length()>140){
                    pretweet = pretweet.substring(0, 136);
                    pretweet = pretweet + "...";
                }
                
                twitter.updateStatus(pretweet);

                System.out.println("Successfully updated the status in Twitter.");
            } catch (TwitterException te) {
                te.printStackTrace();
            }
        }
        
        
    
}
