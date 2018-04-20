import java.util.ArrayList;
import java.util.Random;

	
	
	public class Manipulate {
		
		public Train tr;
		
	public Manipulate(Train t){
		tr = t;
	}
		public void action(ActionCard card, Character player){
			int car = player.getCurrentCar();
			switch(card.getWhatItDoes()){
			case "punch":
				
				ArrayList<Character> list = tr.getTrainCar(car).getPlatform(player.getLevel()).getCharacterList();
				//ASK PLAYER FOR PLAYER TO PUNCH, PUT IN STRING 'VICTIM', ask player for direction to punch, put in string 'direction'
				String victim = "";
				String direction = "";
				for(int x = 0; x < list.size(); x++){
					if(list.get(x).getName().equals(victim)){
						Character vic = player.currentPlat.removePlayer(victim);
						
						if(player.getName().equals("cheyenne")){
							player.addBags(vic.removeBag());
						}
						
						if(direction.equals("right")){
							tr.getTrainCar(player.getCurrentCar()+1).getPlatform(player.currentLevel).addPlayer(vic);
						}
						else if(direction.equals("left")){
							tr.getTrainCar(player.getCurrentCar()-1).getPlatform(player.currentLevel).addPlayer(vic);
						}
					}
				}
			case "shoot":
				if(player.getName().equals("django")){
					
				}
			case "move":{
				String way = "";
				if(way.equals("up")){
					
				}
			}
			case "changeFloor": {
			
				int level = player.currentLevel;
				
				int changeTo = 0;
				
				if (level == 0) {
					
					changeTo = 1;
					
				}
				else {
					
					changeTo = 0;
					
				}
				
				tr.getTrainCar(player.getCurrentCar ()).getPlatform (level).removePlayer(player.getName());
				
				tr.getTrainCar(player.getCurrentCar ()).getPlatform (changeTo).addPlayer (player);
				
				break;
			
			}
			case "loot":
			case "marshall": {
			
				int currentLoc = 0;
				
				int level = 0;
				
				for (int i = 0; i < tr.getTrain().size(); i++) {
										
					ArrayList<Character> c0 = tr.getTrainCar(i).getPlatform(0).getCharacterList();
										
					ArrayList<Character> c1 = tr.getTrainCar(i).getPlatform(1).getCharacterList();
					
					for (int j = 0; j < c0.size (); j++) {
						
						if (c0.get (j).equals ("marshall")) {
							
							currentLoc = i;
							
							level = 0;
							
						}
						
					}
					
					for (int j = 0; j < c1.size (); j++) {
						
						if (c1.get (j).equals ("marshall")) {
							
							currentLoc = i;
							
							level = 1;
							
						}
						
					}
					
					
				}
				
				Character marshall = tr.getTrainCar (currentLoc).getPlatform (level).removePlayer ("marshall");
				
				// ASK PLAYER FOR DIRECTION TO MOVE MARSHALL, PUT "LEFT" OR "RIGHT" (FORWARD OR BACKWARD RESPECTIVELY) (LOWERCASE) IN STRING d
				
				String d = "";
				
				if (d.equals("left") && currentLoc - 1 >= 0) {
					
					tr.getTrainCar (currentLoc - 1).getPlatform(level).addPlayer(marshall);
					
				}
				else if (d.equals("right") && currentLoc + 1 >= 0 && currentLoc + 1 <= 4) {
					
					tr.getTrainCar (currentLoc + 1).getPlatform(level).addPlayer(marshall);
					
				}
				else {
					
					tr.getTrainCar (currentLoc).getPlatform (level).addPlayer(marshall);
					
				}
				
				break;
			
			
			}
			}
		}
	}
