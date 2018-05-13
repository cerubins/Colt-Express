import java.util.ArrayList;
import java.util.Collections;


public class RoundCardSelector {

	public ArrayList <RoundCard> select () {
		
		ArrayList <RoundCard> a = new ArrayList <RoundCard> ();
		
		a.add (new RoundCard ("aloneInCar:StealOneBag", "1 1 0 1", 4, true));
		
		a.add (new RoundCard ("atopCarAndMarshall:DropBag", "1 1 0 1", 4, true));
		
		a.add (new RoundCard ("onOrInEngine:get250Cash", "1 1 0 1", 4, true));
		
		a.add (new RoundCard ("sheriffDropsNewLockBox", "1 0 1 1 1", 5, false));
		
		a.add (new RoundCard ("allPeopleAtopTrainMoveToCaboose", "1 0 1 1", 4, false));
		
		// a.add (new RoundCard ("nothing", "1 0 1 0 1", 5, false));
		
		// a.add (new RoundCard ("nothing", "1 1 1 1", 4, false));
		
		a.add (new RoundCard ("everyoneAtopMovesTowardsEngine1Space", "1 1 1 1", 4, false));
		
		a.add (new RoundCard ("everyoneTakes1Damage", "1 1 0 1 1", 5, false));
		
		a.add (new RoundCard ("sheriffShootsAnyoneAboveHisCar", "1 1 0 2", 4, false));
		
		Collections.shuffle (a);
		
		return a;
		
		//return new RoundCard ("aloneInCar:StealOneBag", "1 1 0 1", 4, true);
		
	}
	
}
