import java.util.ArrayList;
import java.util.Collections;


public class RoundCardSelector {

	public Card select () {
		
		ArrayList <RoundCard> a = new ArrayList <RoundCard> ();
		
		a.add (new RoundCard ("aloneInCar:StealOneBag", 1101, 0, true));
		
		a.add (new RoundCard ("atopCarAndMarshall:DropBag", 1101, 0, true));
		
		a.add (new RoundCard ("onOrInEngine:get250Cash", 1101, 0, true));
		
		a.add (new RoundCard ("sheriffDropsNewLockBox", 10111, 5, false));
		
		a.add (new RoundCard ("allPeopleAtopTrainMoveToCaboose", 1011, 4, false));
		
		a.add (new RoundCard ("nothing", 10101, 5, false));
		
		a.add (new RoundCard ("nothing", 1111, 4, false));
		
		a.add (new RoundCard ("everyoneAtopMovesTowardsEngine1Space", 1111, 4, false));
		
		a.add (new RoundCard ("everyoneTakes1Damage", 11011, 5, false));
		
		a.add (new RoundCard ("sheriffShootsAnyoneAboveHisCar", 1102, 4, false));
		
		Collections.shuffle (a);
		
		return a.get (0);
		
	}
	
}
