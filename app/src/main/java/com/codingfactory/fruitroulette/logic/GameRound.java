//package com.codingfactory.fruitroulette.logic;
//
//import com.codingfactory.fruitroulette.Fruits.Fruit;
//
//import java.util.ArrayList;
//
//public class GameRound {
//
//    private final ArrayList<Integer> pickedArray;
//
//    public GameRound(ArrayList<Integer> pickedArray) {
//        this.pickedArray = pickedArray;
//    }
//
//    public ArrayList<Integer> getPickedArray() {
//        return pickedArray;
//    }
//
//    public void GamePlay(GameIteration currentGameIteration) {
//        ConsoleDisplays.FruitDisplay(currentGameIteration.getRow());
//        int i =0;
//        for (Integer pikedFruit : pickedArray) {
//            if (pikedFruit==currentGameIteration.getRow().get(i).getId()) {
//                currentGameIteration.getRow().get(pickedArray.indexOf(pikedFruit)).setDiscovered(2);
//                currentGameIteration.incFruitDiscovered();
//            }
//            else {
//                for (Fruit rdmFruit : currentGameIteration.getRow()){
//                    if (rdmFruit.getId() == pikedFruit){
//                        currentGameIteration.getRow().get(pickedArray.indexOf(pikedFruit)).setDiscovered(1);
//                        break;
//                    }
//                }
//            }
//            i++;
//        }
//    }
//}
