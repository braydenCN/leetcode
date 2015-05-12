package com.brayden;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class WordBreak {
	
  public boolean wordBreak(String s, Set<String> dict) {
      int len = s.length();
      boolean[] can = new boolean[len + 1];
      can[0] = true;
      for (int i = 1; i <= len; i++)
          for (int j = 0; j < i; j++)
              if (can[j] && dict.contains(s.substring(j, i))){
                  can[i] = true;
                  break;
              }
      return can[len];
  }
    
	private static class Pair{
		int i, j;
		Pair(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	private Set<String> shrinkDict(Set<String> dict, String s){
		Set<String> newSet = new HashSet<>();
		List<Set<String>> setList = new ArrayList<>();
		for(int i = 0; i < 26; i++)
			setList.add(0, new HashSet<String>());
		for(String str: dict)
			setList.get(str.charAt(0) - 'a').add(str);
        for(Set<String> set: setList)
outter:     for(String s1: set){
			    for(String s2: set){
				    if(!s1.equals(s2) && s1.matches("^(?:" + s2 + ")+$"))
					    continue outter;
			    }
			    if(s.contains(s1))
				    newSet.add(s1);
		    }
		return newSet;
	}
	
	public boolean wordBreak1(String s, Set<String> dict) {
        dict = shrinkDict(dict, s);
		Stack<Pair> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++){
			for(int j = i + 1; j <= s.length(); j++){
				if(!dict.contains(s.substring(i, j))){ // no match in dict
					if(j == s.length()){               // get the end
						if(!stack.isEmpty()){          // still got options
							Pair p = stack.pop();
							i = p.i;
							j = p.j;
							continue;
						}else                          // no match
							return false;
					}else                              // extend j
					    continue;
				}
				if(j == s.length())                    // got answer
					return true;
				stack.push(new Pair(i, j));            // for future track
				i = j - 1;
				break;
			}
		}
		return false;
    }

    @Test
	public void test1(){
        assertTrue(wordBreak("", new HashSet<>()));
    }
	
	@Test
	public void test(){
		Set<String> set = new HashSet<>();
		set.add("leet");
		set.add("code");
		assertTrue(wordBreak("leetcode", set));
		
		set.clear();
		set.add("a");
		set.add("b");
		set.add("bbb");
		set.add("bbbb");
		assertTrue(wordBreak("bb", set));
		
		set.clear();
		set.add("a");
		assertTrue(wordBreak("a", set));
		
		set.clear();
		set.add("aaaa");
		set.add("aaa");
		assertTrue(wordBreak("aaaaaaa", set));
		
		/**
		 * 
Submission Result: Time Limit Exceeded
Last executed input: 	"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
		 */
		set.clear();
		set.add("a");
		set.add("aa");
		set.add("aaa");
		set.add("aaaa");
		set.add("aaaaa");
		set.add("aaaaaa");
		set.add("aaaaaaa");
		set.add("aaaaaaaa");
		set.add("aaaaaaaaa");
		set.add("aaaaaaaaaa");
		set.add("aaaaaaaaaaa");
		set.add("aaaaaaaaaaaa");
		assertFalse(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", set));
		
		
		/**
		 * 
Submission Result: Time Limit Exceeded
Last executed input: 	"fajbeokiakfmlacbinjdnjdmmfha", ["be","ellaekgjhibcomc","ahaklkan","jcm","lchidklmcone","ljmdgagaen","giioojldjkfnno","el","eibjaffacjll","hn","hbjakhjneml","foi","nbhf","aigf","cfdlnc","fa","amakgofedhkghgl","ddhmhdhioh","ijoddeabbiei","giamcgco","nholghlfbbendi","emlc","m","elgibme","behkignjenmh","lodkkjgioe","doe","hgflgakna","macghogdidmdm","ec","kncigolkog","ljooio","lch","gkaclkbjn","ofiaglfoffl","alhfbb","cfmdbgo","cfcnajknk","jfh","almbgdjnbbbgmhb","dmlnnohf","olojeejfafc","ndgcmgoe","cmkdjilfeo","bengdd","enfg","dbngiggni","anmkljn","njdnjdmmfha","ndimmddfmhe","hmkdjkhhiilnf","ehd","jfdl","dlgki","bhoflnomibkki","lg","fjojjnnkdfenhol","lefhhl","nimdl","gejgomblmim","ahbnlmlfmejjj","glhacaojnf","mfjdhnhdkm","do","fnh","mnmjdk","hfjgdlnnb","hniolfhkhbie","ldgodonogcab","mabjnnohnijhn","aceojlkmdg","aedfljg","cehk","jag","oniegflnhje","jo","maokc","jkbndc","djbn","dajkdblojkf","dmen","kcdjdocinenc","cgindbm","h","odaof","cmogcbgj","anjahlgbbmba","haoe","ggacnminj","ilcfjoedhe","klookammgofl","onnmenn","mbdneaioo","jc","dekgil","bjdfibfd","hfbnlgmlllcb","afebehf","obekljbnh","eoaedhjk","nobamccd","mdieojoknf","komcglmakkaa","jcliimcc","jmmgbmha","gdogjnn","ednembco","dgno","jiaheeabifahfmo","djkcgnakkh","kdkiglgf","eb","fmdnlhj","eicohdciejc","jgofmankkf","o","nnmomkmkmiaoga","njchkccln","ndamha","eleanmojdi","ebkl","jcageehlelcg","acfddofjihgmn","iklaomfhjm","io","igmob","lfnhnlnigbbignk","anihfojmedf","nj","oilcabalhb","adjfbkfjch","lbfb","mgfnngfccb","jhmhggm","dnllc","c","ljim","jmikd","mdfimdgac","fhbclgo","edclcdia","nelmfjejff","i","cmcbbckdnjcoo","cddocce","hc","keh","keofhnhemd","biln","mjcnbjmkikon","fekbdnkolahh","hkfmjbj","mjoj","jn","ilof","ifhfk","aofmg","nofljgmmmf","hcdifeiclbchlf","imlijgdg","ocdiiemcmbkglm","nhoekmlkjfoa","kibffkbleedda","kdhdjekccbkc","bcbflcag","jekmkdimnnjjoo","mmgfljchalbem","kchk","oi","ncf","jembgfa","l","kfkeianmmmdacl","ecjkkfggj","jdgcfnhfjonkhig","jhagiokii","nifm","bbjjlj","adajlokomibfg","ojk","lockdel","bh","hoojolglchck","conko","eadi","kfigoijnfimolen","g","dbnj","cojkbmo","hh","mcdbh","ngdmgioen","ehjagfohnolkho","dgfgdlc","aoglneoh","gbc","ijjckddeicld","imekih","liiaecniil","hahejbhgiclb","fnmojm","ablihjhggiahhno","colloaakco","jhobddaanbhmlg","cbfajfhkoh","cim","laghknigabn","dcbnbkegkjam","gem","ljjim","icclogji","omidhe","f","giiaclfcjkagl","ndcjldekjnkekm","aiikdccohcj","mkbmb","oomhhafobic","bkacdjfgbggn","ahghdoahbi","hedm","eeoj","bdgdlfollegej","eg","dfeb","dkffkid","hcne","gjkohnaaabn","jfeododjgdhlfbf","clfkmconnkfb","abnbkcni","hk","ghnmhjm","oibjibmkaibdefa","hjambim","oe","aao","jil","fmhomflfen","hlidcklnmb","hiaonkhd","bibbmkandf","hke","bmfcionm","inhcnlkbkkmjicn","jckjedhgoghi","chmik","mnjldknhaec","hocbccbg","ljadj","lciikgnlj","ifjjhkbhifione","foikblanoco","ode","mjc","fhklofh","mmoklkkog","hocbojmhffeajo","ccmmd","bkkh","nhhgcflniebkme","lfohikenfbjacli","cmehijnihijgng","caa","bmk","emofof","jjagiogohfab","ibh","eoacdlnodalkjbl","cbbjbbnjom","iljiomeloehen","gignlngclmh","b","ll","dokgngnocde","cienegffibgieba","agbachloidg","mlelnafokd","nmcmka","akeogjbjcnf","nebdic","a","efc","ljdk","jhcag","bkbikbjgae","mcjlgjeo","lo","dbiofobl","cehebiljff","eeagngm","ondahcjiel","coblanndhlhoggj","jaobmjml","jfejjinofek","hhnna","gdhcn","acelcomgkgohm","njkkjkkln","jmc","hkekoho","boefec","cioibfgjmhb","ebggdbeimn","emhg","cfghkhii","d","k","khoddedia","nhje","eebkfml","bohhd","kg","n","ilgemokdehcbaif","cldicda","einij","akmabcgfn","fmkmcn","bnlfbagkke","oakbgjejmcncj","iehdfadgoik","kkcfo","jmjkmfcacjjnd","ndokhh","hjfeelhckkjjmj","dnomohejbodkb","jcmblncjadno","oiofcodobiml","ddmillkncjfdfj","aihenmkdnhdhkhf","bfdbakeilfdojnc","jjhbkbne","aigabk","cae","oednojjb","gdoe","jokjceohkmbm","offkanbahigo","kfomigbfddjli","dkkjobgkcejei","mdilld","bofkika","kkinig","cljcflbghjmhmke","kmbjlgdcbdjn","bkgbmoahda","kmnajjdemggnfg","mgjndldil","iemb","kehaokgjg","icign","oijmaolehmoo","amhgldifmgekhe","diacnollhi","lnjhdaafadl","bdfiackhogoje","ebjlfa","deabkgfhnead","gadcob","haa","dbhnbhjcmmab","bbmjainilbbej","dc","bgjgafnjjflne","ehholgnn","fmhccbnc","mdnfl","feeejdgc","mfhlobdadooh","ojna","gkgjnijdbgo","ghngnhn","nhnjaiaadiedgg","nk","hmd","nmbmijaffogl","onkcgbgmago","gfli","ofjlec","nlfnbkkdc","hakilani","bofjdjkhllb","ocjncleljnecfc","gdonnkodmkejhf","fiflchanfllgnf","kaejakoibgln","hmdlfioacgaci","honmfbcog","mlacbi","gf","ejbbemoeha","acfjegee","lllflaocnnkeadi","mdgoebfgacecmbg","faejgln","kmlmhffgcmekm","akcjmgdg","kmhhh","fdohjehacdln","e","ojba","ohadmod","eaenkdiaokl","dii","cgfjaklblafeifo","imoeflkcgbbem","nbjkmb","jjgm","hofgelg","cnihecmdigdgflg","fnmikkeldjgb","onlhgonldjaedh","fmkdn","kfbcbleen","oejioibnmab","cg","meadghbocjnj","hmmdnkegfeieijn","ijgenomhndlje","maccdcgfjig","iabemie","mlfg","mdblmdaechmeaml","dhlafgjo","eabbiila","kf","oehggehfmijlfmg","klljaejidfhbon","akmbgmignoag","jgbkngmigdfm","kjeelnbn","ajaa","mlcjoiaahoiga","oalnielba","ffmobgkc","kmhoknfffdmo","nagjiffnjhh","dlehllomjok","agaejefhdbk","nnegoijfdj","ndl","dhfginocgi","nflmglgh","bcd","gbgjijemmdio","jk","gidgjbmb","hi","lmgoah","fdebefcech","ach","bahaoj","ccmmblgibgjahi","moid","jhilgedidndm","ldiakemnj","bbnibccm","jkbneoaheaajnm","clkgmbjlgdnl","lobbdldifnnijh","dnmih","jglia","didicmghfe","dlhbcfclf","akbmioocoihkfh","foofdldm","imenimfcame","ifekbmgnbdkc","jjlkaabdollola","gie","hbaj","noomfnfccmgaa","dcjffeg","nb","obdb","lolgjflimkib","eaiigminlakkb","cia","hkf","jknfklaio","igklbiomo","jfjgh","ekgnkfnhjcch","kmonfcclieehlik","oggkmccklnmj","bedhobcl","egmnhajcnhcdgb","imfdhekamfel","bmmkhfdbm","gnjfbcjlecfn","llmkgclm","gafinbnhfe","mlbfedkoeeddfao","kklcdmglleb","ckekmeiea","mi","kfejn","lm","mk","abkoajocfdili","jidac","jonhkanccl","lllodjgnmm","abfeaodlmjkngol","cdncnh","lkcb","abhilnmmhijab","hiljkfakojjld","mbboobkaolkljo","jhkblobaofgoh","ncm","mgbdhmcicomf","oag","akmjdd","abkenodnhj","mljf","afb","afejkobmiffeee","oollnkilabmb","gfaocokmcmjlmb","cokmecdd","bo","endocdnmjiek","bcf","lhllbagiel","bhihgofhj","ffce","neio","ofbfiiab","kjdo","lgfggnamceeo","kofledoinamcj","femhndomndoakoa","fmodaigcka","omakggcalhn","hhhogmcbjnhelkk","mgah","jghjjfmk","ecolelfmcb","eajjkdncafhhgab","obno","fifigfeok","laafjimienff","beckbbmhmofb","nafhihmgnikd","cbcfnhlkne","kao","nlkfhbm","fmh","ohfek","oj","hifgcgi","adhkn","lffgmodeafnn","ngchmhdbmhmhh","mcffimhnlffab","blhmkdhbnhbb","kkb","lgkine","hgfbdbfffanhik","joebhbh","img","kglcddmloo","hoflgfao","bdhgdekb","mggflahnoo","cmnol","imnmmgimmedf","mcjmoofomiia","mlakhbjfnbmgena","ilhmcnkkeg","domhbmkcd","fco","fdio","cmkoagblnd","kmihfigmceiiicm","afgbadbgbaon","menahlemehifooe","jacokdiiokaic","limj","fkedaoomokjbkdi","jkncd","jblmcmfnegnk","jjicjhjhbg","gbfcead","jf","aifnkmnao","effmhlhchngknl","odhjeib","ohcgmgb","bgbd","am","kkjfbdlh","hgbjakkokjgooel","jbeokiakf","flaoba","cifcdnanmk","mice","ihhofdai","ldnfmeiemhf","kefbbohhgineacj","bi","njfie","ociodahlomoekkf","andhoindeca","ajnndjocjeg","bmijkmjbbkgbbh","feanh","bjemcefkfcaenal","edfdenghinm","moal","ndbjdmijh","enccnhmoifa","dbckadjibam","gd","oglj","aldjelhbemle","cmbkofkcoe","ihciacibeh","lcojkclhmibgoif","jfmjncnolfj","gfcmcabhjki","aggfmakaanjb","mhbelld","hon","nkfoikcddehcah","kggbigknacmohb","jbkgndofcmaaohh","gkjano","afhhhh","mjng","jilckm","dekkedjehmenbm","clfm","acmhbkdadgena","oenokachg","lhiea","dceiag","eebgj","oolifidh","dj","cdfn","eghdglgiok","jdhegkefhbdhkm","mhgngafea","akabbcjkdnbc","gcbn","kimdgahf","oc"]
		 */
		String[] arr = new String[]{"be","ellaekgjhibcomc","ahaklkan","jcm","lchidklmcone","ljmdgagaen","giioojldjkfnno","el","eibjaffacjll","hn","hbjakhjneml","foi","nbhf","aigf","cfdlnc","fa","amakgofedhkghgl","ddhmhdhioh","ijoddeabbiei","giamcgco","nholghlfbbendi","emlc","m","elgibme","behkignjenmh","lodkkjgioe","doe","hgflgakna","macghogdidmdm","ec","kncigolkog","ljooio","lch","gkaclkbjn","ofiaglfoffl","alhfbb","cfmdbgo","cfcnajknk","jfh","almbgdjnbbbgmhb","dmlnnohf","olojeejfafc","ndgcmgoe","cmkdjilfeo","bengdd","enfg","dbngiggni","anmkljn","njdnjdmmfha","ndimmddfmhe","hmkdjkhhiilnf","ehd","jfdl","dlgki","bhoflnomibkki","lg","fjojjnnkdfenhol","lefhhl","nimdl","gejgomblmim","ahbnlmlfmejjj","glhacaojnf","mfjdhnhdkm","do","fnh","mnmjdk","hfjgdlnnb","hniolfhkhbie","ldgodonogcab","mabjnnohnijhn","aceojlkmdg","aedfljg","cehk","jag","oniegflnhje","jo","maokc","jkbndc","djbn","dajkdblojkf","dmen","kcdjdocinenc","cgindbm","h","odaof","cmogcbgj","anjahlgbbmba","haoe","ggacnminj","ilcfjoedhe","klookammgofl","onnmenn","mbdneaioo","jc","dekgil","bjdfibfd","hfbnlgmlllcb","afebehf","obekljbnh","eoaedhjk","nobamccd","mdieojoknf","komcglmakkaa","jcliimcc","jmmgbmha","gdogjnn","ednembco","dgno","jiaheeabifahfmo","djkcgnakkh","kdkiglgf","eb","fmdnlhj","eicohdciejc","jgofmankkf","o","nnmomkmkmiaoga","njchkccln","ndamha","eleanmojdi","ebkl","jcageehlelcg","acfddofjihgmn","iklaomfhjm","io","igmob","lfnhnlnigbbignk","anihfojmedf","nj","oilcabalhb","adjfbkfjch","lbfb","mgfnngfccb","jhmhggm","dnllc","c","ljim","jmikd","mdfimdgac","fhbclgo","edclcdia","nelmfjejff","i","cmcbbckdnjcoo","cddocce","hc","keh","keofhnhemd","biln","mjcnbjmkikon","fekbdnkolahh","hkfmjbj","mjoj","jn","ilof","ifhfk","aofmg","nofljgmmmf","hcdifeiclbchlf","imlijgdg","ocdiiemcmbkglm","nhoekmlkjfoa","kibffkbleedda","kdhdjekccbkc","bcbflcag","jekmkdimnnjjoo","mmgfljchalbem","kchk","oi","ncf","jembgfa","l","kfkeianmmmdacl","ecjkkfggj","jdgcfnhfjonkhig","jhagiokii","nifm","bbjjlj","adajlokomibfg","ojk","lockdel","bh","hoojolglchck","conko","eadi","kfigoijnfimolen","g","dbnj","cojkbmo","hh","mcdbh","ngdmgioen","ehjagfohnolkho","dgfgdlc","aoglneoh","gbc","ijjckddeicld","imekih","liiaecniil","hahejbhgiclb","fnmojm","ablihjhggiahhno","colloaakco","jhobddaanbhmlg","cbfajfhkoh","cim","laghknigabn","dcbnbkegkjam","gem","ljjim","icclogji","omidhe","f","giiaclfcjkagl","ndcjldekjnkekm","aiikdccohcj","mkbmb","oomhhafobic","bkacdjfgbggn","ahghdoahbi","hedm","eeoj","bdgdlfollegej","eg","dfeb","dkffkid","hcne","gjkohnaaabn","jfeododjgdhlfbf","clfkmconnkfb","abnbkcni","hk","ghnmhjm","oibjibmkaibdefa","hjambim","oe","aao","jil","fmhomflfen","hlidcklnmb","hiaonkhd","bibbmkandf","hke","bmfcionm","inhcnlkbkkmjicn","jckjedhgoghi","chmik","mnjldknhaec","hocbccbg","ljadj","lciikgnlj","ifjjhkbhifione","foikblanoco","ode","mjc","fhklofh","mmoklkkog","hocbojmhffeajo","ccmmd","bkkh","nhhgcflniebkme","lfohikenfbjacli","cmehijnihijgng","caa","bmk","emofof","jjagiogohfab","ibh","eoacdlnodalkjbl","cbbjbbnjom","iljiomeloehen","gignlngclmh","b","ll","dokgngnocde","cienegffibgieba","agbachloidg","mlelnafokd","nmcmka","akeogjbjcnf","nebdic","a","efc","ljdk","jhcag","bkbikbjgae","mcjlgjeo","lo","dbiofobl","cehebiljff","eeagngm","ondahcjiel","coblanndhlhoggj","jaobmjml","jfejjinofek","hhnna","gdhcn","acelcomgkgohm","njkkjkkln","jmc","hkekoho","boefec","cioibfgjmhb","ebggdbeimn","emhg","cfghkhii","d","k","khoddedia","nhje","eebkfml","bohhd","kg","n","ilgemokdehcbaif","cldicda","einij","akmabcgfn","fmkmcn","bnlfbagkke","oakbgjejmcncj","iehdfadgoik","kkcfo","jmjkmfcacjjnd","ndokhh","hjfeelhckkjjmj","dnomohejbodkb","jcmblncjadno","oiofcodobiml","ddmillkncjfdfj","aihenmkdnhdhkhf","bfdbakeilfdojnc","jjhbkbne","aigabk","cae","oednojjb","gdoe","jokjceohkmbm","offkanbahigo","kfomigbfddjli","dkkjobgkcejei","mdilld","bofkika","kkinig","cljcflbghjmhmke","kmbjlgdcbdjn","bkgbmoahda","kmnajjdemggnfg","mgjndldil","iemb","kehaokgjg","icign","oijmaolehmoo","amhgldifmgekhe","diacnollhi","lnjhdaafadl","bdfiackhogoje","ebjlfa","deabkgfhnead","gadcob","haa","dbhnbhjcmmab","bbmjainilbbej","dc","bgjgafnjjflne","ehholgnn","fmhccbnc","mdnfl","feeejdgc","mfhlobdadooh","ojna","gkgjnijdbgo","ghngnhn","nhnjaiaadiedgg","nk","hmd","nmbmijaffogl","onkcgbgmago","gfli","ofjlec","nlfnbkkdc","hakilani","bofjdjkhllb","ocjncleljnecfc","gdonnkodmkejhf","fiflchanfllgnf","kaejakoibgln","hmdlfioacgaci","honmfbcog","mlacbi","gf","ejbbemoeha","acfjegee","lllflaocnnkeadi","mdgoebfgacecmbg","faejgln","kmlmhffgcmekm","akcjmgdg","kmhhh","fdohjehacdln","e","ojba","ohadmod","eaenkdiaokl","dii","cgfjaklblafeifo","imoeflkcgbbem","nbjkmb","jjgm","hofgelg","cnihecmdigdgflg","fnmikkeldjgb","onlhgonldjaedh","fmkdn","kfbcbleen","oejioibnmab","cg","meadghbocjnj","hmmdnkegfeieijn","ijgenomhndlje","maccdcgfjig","iabemie","mlfg","mdblmdaechmeaml","dhlafgjo","eabbiila","kf","oehggehfmijlfmg","klljaejidfhbon","akmbgmignoag","jgbkngmigdfm","kjeelnbn","ajaa","mlcjoiaahoiga","oalnielba","ffmobgkc","kmhoknfffdmo","nagjiffnjhh","dlehllomjok","agaejefhdbk","nnegoijfdj","ndl","dhfginocgi","nflmglgh","bcd","gbgjijemmdio","jk","gidgjbmb","hi","lmgoah","fdebefcech","ach","bahaoj","ccmmblgibgjahi","moid","jhilgedidndm","ldiakemnj","bbnibccm","jkbneoaheaajnm","clkgmbjlgdnl","lobbdldifnnijh","dnmih","jglia","didicmghfe","dlhbcfclf","akbmioocoihkfh","foofdldm","imenimfcame","ifekbmgnbdkc","jjlkaabdollola","gie","hbaj","noomfnfccmgaa","dcjffeg","nb","obdb","lolgjflimkib","eaiigminlakkb","cia","hkf","jknfklaio","igklbiomo","jfjgh","ekgnkfnhjcch","kmonfcclieehlik","oggkmccklnmj","bedhobcl","egmnhajcnhcdgb","imfdhekamfel","bmmkhfdbm","gnjfbcjlecfn","llmkgclm","gafinbnhfe","mlbfedkoeeddfao","kklcdmglleb","ckekmeiea","mi","kfejn","lm","mk","abkoajocfdili","jidac","jonhkanccl","lllodjgnmm","abfeaodlmjkngol","cdncnh","lkcb","abhilnmmhijab","hiljkfakojjld","mbboobkaolkljo","jhkblobaofgoh","ncm","mgbdhmcicomf","oag","akmjdd","abkenodnhj","mljf","afb","afejkobmiffeee","oollnkilabmb","gfaocokmcmjlmb","cokmecdd","bo","endocdnmjiek","bcf","lhllbagiel","bhihgofhj","ffce","neio","ofbfiiab","kjdo","lgfggnamceeo","kofledoinamcj","femhndomndoakoa","fmodaigcka","omakggcalhn","hhhogmcbjnhelkk","mgah","jghjjfmk","ecolelfmcb","eajjkdncafhhgab","obno","fifigfeok","laafjimienff","beckbbmhmofb","nafhihmgnikd","cbcfnhlkne","kao","nlkfhbm","fmh","ohfek","oj","hifgcgi","adhkn","lffgmodeafnn","ngchmhdbmhmhh","mcffimhnlffab","blhmkdhbnhbb","kkb","lgkine","hgfbdbfffanhik","joebhbh","img","kglcddmloo","hoflgfao","bdhgdekb","mggflahnoo","cmnol","imnmmgimmedf","mcjmoofomiia","mlakhbjfnbmgena","ilhmcnkkeg","domhbmkcd","fco","fdio","cmkoagblnd","kmihfigmceiiicm","afgbadbgbaon","menahlemehifooe","jacokdiiokaic","limj","fkedaoomokjbkdi","jkncd","jblmcmfnegnk","jjicjhjhbg","gbfcead","jf","aifnkmnao","effmhlhchngknl","odhjeib","ohcgmgb","bgbd","am","kkjfbdlh","hgbjakkokjgooel","jbeokiakf","flaoba","cifcdnanmk","mice","ihhofdai","ldnfmeiemhf","kefbbohhgineacj","bi","njfie","ociodahlomoekkf","andhoindeca","ajnndjocjeg","bmijkmjbbkgbbh","feanh","bjemcefkfcaenal","edfdenghinm","moal","ndbjdmijh","enccnhmoifa","dbckadjibam","gd","oglj","aldjelhbemle","cmbkofkcoe","ihciacibeh","lcojkclhmibgoif","jfmjncnolfj","gfcmcabhjki","aggfmakaanjb","mhbelld","hon","nkfoikcddehcah","kggbigknacmohb","jbkgndofcmaaohh","gkjano","afhhhh","mjng","jilckm","dekkedjehmenbm","clfm","acmhbkdadgena","oenokachg","lhiea","dceiag","eebgj","oolifidh","dj","cdfn","eghdglgiok","jdhegkefhbdhkm","mhgngafea","akabbcjkdnbc","gcbn","kimdgahf","oc"};
	    assertTrue(wordBreak("fajbeokiakfmlacbinjdnjdmmfha", new HashSet<String>(Arrays.asList(arr))));
	}
	

}
