package Twitter;

import dominio.Oportunidade;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author 152072
 */
public class Tweet {
        // Chaves de ativação
        static String consumerKeyStr =       "ssSa9oGptlEWd5HZhMLv3fvAY";
	static String consumerSecretStr =    "zb9zKZsRYZyUry4GkDWXdA3H71RmMBb4gorfsU09iXouxEGRIG";
	static String accessTokenStr =       "925046275610947584-knr4EDD1Bd1JRtf5KashL6w5CYECL5C";
	static String accessTokenSecretStr = "4Yd2cBFx1uyjVYuCKNDZo7lqL22ziUXWTusKO2N738XxB";
        
        public void Tweet(Oportunidade op) {
            //op.getDescricao();
                    
            try {
                // inicia conexão
                Twitter twitter = new TwitterFactory().getInstance();

                twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
                AccessToken accessToken = new AccessToken(accessTokenStr,
                        accessTokenSecretStr);

                twitter.setOAuthAccessToken(accessToken);
                // inicia conexão
                
                //cria tweet
                String pretweet = Timestamp.valueOf(LocalDateTime.now())+"\n"+
                        "Nova oportunidade de cargo "+op.getCargo().getDescricao()+" está disponivel."+
                        "\n\n"+op.getDescricao();
                
                // formata tweet
                if (pretweet.length()>280){
                    pretweet = pretweet.substring(0, 286);
                    pretweet = pretweet + "...";
                }
                
                // envia tweet
                twitter.updateStatus(pretweet);

                // mensagem para o sistema
                System.out.println("Successfully updated the status in Twitter.");
            } catch (TwitterException te) {
                te.printStackTrace();
            }
        }
        
        
    
}
