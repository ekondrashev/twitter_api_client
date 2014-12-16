package twitter;

import net.unto.twitter.Api;

public class Ghgh {

	public static void main(String[] args) {
		
		Api api = Api.builder().username("KateVarlamova").password("Katusha").build();
		api.updateStatus("Just test").build().post();
	}

}
