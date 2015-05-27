package com.brayden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.Test;

public class TheSkylineProblem {
    
    private static class Heap{
        private static class Item{
            int key, count = 1;

            public Item(int key) {
                this.key = key;
            }
        }
        
        private Item[] arr = new Item[10100];
        private Map<Integer, Integer> map = new HashMap<>();
        private int size = 0;
        
        private int leftChild(int ind){
            return ind * 2 + 1;
        }
        
        private int rightChild(int ind){
            return ind * 2 + 2;
        }
        
        private boolean hasLeftChild(int ind){
            return leftChild(ind) < size;
        }
        
        private boolean hasRightChild(int ind){
            return rightChild(ind) < size;
        }

        private int parent(int ind){
            return (ind - 1) / 2;
        }
        
        private void swap(int i1, int i2) {
            Item tmp = arr[i1];
            arr[i1]  = arr[i2];
            arr[i2]  = tmp;
         }
        
        private void siftDown(int ind){
            while(hasLeftChild(ind)){
                int child = leftChild(ind);
                if(hasRightChild(ind) && arr[child + 1].key > arr[child].key)
                    child++; // compare with right child now
                if(arr[ind].key >= arr[child].key)
                    break;
                map.put(arr[child].key, ind);
                swap(ind, child);
                ind = child;
            }
            map.put(arr[ind].key, ind);
        }
        
        private void siftUp(int ind){
            while(ind > 0){
                int parent = parent(ind);
                if(arr[ind].key <= arr[parent].key)
                    break;
                map.put(arr[parent].key, ind);
                swap(ind, parent);
                ind = parent;
            }
            map.put(arr[ind].key, ind);
        }
        
        void offer(int key){
            if(map.containsKey(key))
                arr[map.get(key)].count++;
            else{
                arr[size++] = new Item(key);
                siftUp(size - 1);
            }
        }
        
        void remove(int key){
            if(!map.containsKey(key))
                return;
            int ind = map.get(key);
            arr[ind].count--;
            if(arr[ind].count == 0){
                map.remove(key);
                size--;
                if(ind == size)
                    return;
                swap(ind, size);
                if(ind > 0 && arr[ind].key > arr[parent(ind)].key)
                    siftUp(ind);
                else
                    siftDown(ind);
            }
        }
        
        boolean isEmpty(){
            return size == 0;
        }
        
        int peek(){
            return arr[0].key;
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<Point> list = new ArrayList<>();
        for(int[] building: buildings){
            list.add(new Point(building[0], building[2], true));
            list.add(new Point(building[1], building[2], false));
        }
        list.sort((p1, p2) -> {
            if(p1.loc != p2.loc)
                return p1.loc - p2.loc;
            if(p1.isLeft != p2.isLeft)
                return p1.isLeft ? 1 : -1;
            return p1.height - p2.height;
        });
        
        LinkedList<int[]> result = new LinkedList<>();
        Heap heap = new Heap();
        for(Point p: list){
            int[] toAdd = null;
            if(p.isLeft){
                if(heap.isEmpty() || p.height > heap.peek())
                    toAdd = new int[]{p.loc, p.height};
                heap.offer(p.height);
            }else{
                heap.remove(p.height);
                if(heap.isEmpty())
                    toAdd = new int[]{p.loc, 0};
                else if(p.height > heap.peek())
                    toAdd = new int[]{p.loc, heap.peek()};
            }
            if(toAdd == null)
                continue;
            if(!result.isEmpty() && toAdd[0] == result.getLast()[0])
                result.removeLast();
            if(result.isEmpty() || toAdd[1] != result.getLast()[1]){
                result.add(toAdd);
            }
        }
        return result;
    }
    
    
    private static class Point{
        int loc, height;
        boolean isLeft;
        public Point(int loc, int height, boolean isLeft) {
            super();
            this.loc = loc;
            this.height = height;
            this.isLeft = isLeft;
        }
    }
    public List<int[]> getSkyline2(int[][] buildings) {
        List<Point> list = new ArrayList<>();
        for(int[] building: buildings){
            list.add(new Point(building[0], building[2], true));
            list.add(new Point(building[1], building[2], false));
        }
        list.sort((p1, p2) -> {
            if(p1.loc != p2.loc)
                return p1.loc - p2.loc;
            if(p1.isLeft != p2.isLeft)
                return p1.isLeft ? 1 : -1;
            return p1.height - p2.height;
        });
        
        LinkedList<int[]> result = new LinkedList<>();
        PriorityQueue<Integer> pq = 
                new PriorityQueue<>(Collections.reverseOrder());
        for(Point p: list){
            int[] toAdd = null;
            if(p.isLeft){
                if(pq.isEmpty() || p.height > pq.peek())
                    toAdd = new int[]{p.loc, p.height};
                pq.offer(p.height);
            }else{
                pq.remove(p.height);
                if(pq.isEmpty())
                    toAdd = new int[]{p.loc, 0};
                else if(p.height > pq.peek())
                    toAdd = new int[]{p.loc, pq.peek()};
            }
            if(toAdd == null)
                continue;
            if(!result.isEmpty() && toAdd[0] == result.getLast()[0])
                result.removeLast();
            if(result.isEmpty() || toAdd[1] != result.getLast()[1])
                result.add(toAdd);
        }
        return result;
    }
    
    public List<int[]> getSkyline1(int[][] buildings) {
        if(buildings == null || buildings.length == 0)
            return Collections.emptyList();
        int len = buildings.length;
        LinkedList<int[]> result = new LinkedList<>();
        int rightmost = -1;
        
        for(int i = 0; i < len; i++){
            int[] building = buildings[i];
            int left = building[0], right = building[1], height = building[2];
            if(left >= rightmost){
                if(left == rightmost)
                    result.removeLast();
                if(left != rightmost || height != result.getLast()[1]) 
                    result.add(new int[]{left, height});
                result.add(new int[]{right, 0});
                rightmost = right;
                continue;
            }
            List<int[]> tmp = new LinkedList<>();
            for(int j = 0; j < result.size() - 1; j++){
                int[] cur = result.get(j), next = result.get(j + 1);
                if(cur[0] <= left || cur[0] >= right){
                    if(cur[0] == left && cur[1] < height)
                        cur[1] = height;
                    tmp.add(cur);
                }
                if(cur[0] < left && next[0] > left && cur[1] < height)
                    tmp.add(new int[]{left, height});
                if(cur[0] > left && cur[0] < right && cur[1] > height)
                    tmp.add(cur);
                if(cur[0] < right && next[0] > right && cur[1] < height)
                    tmp.add(new int[]{right, cur[1]});
            }
            if(right > rightmost){
                if(height < result.get(result.size() - 2)[1]){
                    result.getLast()[1] = height;
                    tmp.add(result.getLast());
                }
                tmp.add(new int[]{right, 0});
                rightmost = right;
            }else
                tmp.add(result.getLast());
            result.clear();
            result.addAll(tmp);
        }
        return result;
    }
    
    @Test
    public void test(){
        getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
    }
    
    @Test
    public void test1(){
        /**
Submission Result: Runtime Error
Runtime Error Message:  Line 37: java.lang.IndexOutOfBoundsException: Index: -1, Size: 1
Last executed input:    [[0,3,3],[1,5,3],[2,4,3],[3,7,3]]
         */
        getSkyline(new int[][]{{0,3,3},{1,5,3},{2,4,3},{3,7,3}});
    }
    
    @Test
    public void test2(){
        /**
Submission Result: Wrong Answer
Input:  [[0,2,3],[2,5,3]]
Output:     [[0,3],[2,0],[2,3],[5,0]]
Expected:   [[0,3],[5,0]]
         */
        getSkyline(new int[][]{{0,2,3},{2,5,3}});
    }
    
    @Test
    public void test3(){
        /**
Submission Result: Wrong Answer
Input:  [[6765,184288,53874],[13769,607194,451649],[43325,568099,982005],[47356,933141,123943],[59810,561434,119381],[75382,594625,738524],[111895,617442,587304],[143767,869128,471633],[195676,285251,107127],[218793,772827,229219],[316837,802148,899966],[329669,790525,416754],[364886,882642,535852],[368825,651379,6209],[382318,992082,300642],[397203,478094,436894],[436174,442141,612149],[502967,704582,918199],[503084,561197,625737],[533311,958802,705998],[565945,674881,149834],[615397,704261,746064],[624917,909316,831007],[788731,924868,633726],[791965,912123,438310]]
Output:     [[6765,53874],[13769,451649],[43325,982005],[568099,918199],[704582,899966],[802148,831007],[909316,705998],[958802,612149],[992082,0]]
Expected:   [[6765,53874],[13769,451649],[43325,982005],[568099,918199],[704582,899966],[802148,831007],[909316,705998],[958802,300642],[992082,0]]
         */
        getSkyline(new int[][]{{6765,184288,53874},{13769,607194,451649},{43325,568099,982005},{47356,933141,123943},{59810,561434,119381},{75382,594625,738524},{111895,617442,587304},{143767,869128,471633},{195676,285251,107127},{218793,772827,229219},{316837,802148,899966},{329669,790525,416754},{364886,882642,535852},{368825,651379,6209},{382318,992082,300642},{397203,478094,436894},{436174,442141,612149},{502967,704582,918199},{503084,561197,625737},{533311,958802,705998},{565945,674881,149834},{615397,704261,746064},{624917,909316,831007},{788731,924868,633726},{791965,912123,438310}});
    }
    
    @Test
    public void test4(){
        /**
Submission Result: Wrong Answer
Input:  [[1544,231205,376017],[5998,879527,609178],[6145,335251,620022],[8399,852423,441500],[13985,246050,198772],[17311,384461,443954],[19857,545024,924768],[24545,512360,671435],[24637,437312,81524],[34431,410117,572660],[41956,357203,120663],[42582,439404,999964],[45017,495896,997381],[59554,425295,694713],[59749,675665,373880],[64004,876809,401511],[68655,609957,925100],[69029,470794,244859],[72917,320609,664013],[90240,858350,481221],[98512,257365,34944],[98897,617561,110896],[106386,757473,895370],[138505,778541,892102],[142803,482559,915161],[144757,224108,307402],[148962,798847,298420],[153916,608978,659252],[159078,780631,595138],[162335,641684,984370],[169278,520201,615623],[170632,951441,154717],[172091,990955,831470],[177118,614232,815698],[177303,792498,92042],[196888,223796,887790],[200374,903736,16440],[211537,988567,863931],[231437,458146,898771],[239972,855083,850367],[247798,704768,652870],[257551,569479,328756],[261295,627086,545380],[262981,826808,237936],[267002,438552,871785],[271467,592302,176734],[279209,880256,370636],[290082,738236,759585],[308221,553121,306184],[327430,380670,616140],[329228,579494,219292],[335392,940063,872524],[342607,789259,647865],[343982,610053,473003],[351572,710271,234191],[355001,378517,833822],[356988,988350,98517],[373321,727994,205027],[399866,834707,234731],[409207,934136,596974],[412413,593104,628468],[421212,864982,982652],[442333,923180,176317],[442946,801401,799978],[447411,715053,155265],[461090,933427,360992],[471549,665260,955076],[499921,587218,130159],[502274,906023,272847],[505897,821494,809118],[512428,705462,965658],[535788,541703,92423],[555687,960113,583425],[564227,805666,405427],[575074,612835,899325],[629330,797045,84784],[629718,828687,181137],[640143,711963,50666],[643572,832599,301279],[693513,835058,190184],[707821,925059,8606],[720443,971000,127523],[738423,953410,112865],[819134,914252,730415]]
Output:     [[1544,376017],[5998,609178],[6145,620022],[19857,924768],[42582,999964],[439404,997381],[495896,984370],[641684,982652],[864982,892102],[990955,0]]
Expected:   [[1544,376017],[5998,609178],[6145,620022],[19857,924768],[42582,999964],[439404,997381],[495896,984370],[641684,982652],[864982,872524],[940063,863931],[988567,831470],[990955,0]]
         */
        getSkyline(new int[][]{{1544,231205,376017},{5998,879527,609178},{6145,335251,620022},{8399,852423,441500},{13985,246050,198772},{17311,384461,443954},{19857,545024,924768},{24545,512360,671435},{24637,437312,81524},{34431,410117,572660},{41956,357203,120663},{42582,439404,999964},{45017,495896,997381},{59554,425295,694713},{59749,675665,373880},{64004,876809,401511},{68655,609957,925100},{69029,470794,244859},{72917,320609,664013},{90240,858350,481221},{98512,257365,34944},{98897,617561,110896},{106386,757473,895370},{138505,778541,892102},{142803,482559,915161},{144757,224108,307402},{148962,798847,298420},{153916,608978,659252},{159078,780631,595138},{162335,641684,984370},{169278,520201,615623},{170632,951441,154717},{172091,990955,831470},{177118,614232,815698},{177303,792498,92042},{196888,223796,887790},{200374,903736,16440},{211537,988567,863931},{231437,458146,898771},{239972,855083,850367},{247798,704768,652870},{257551,569479,328756},{261295,627086,545380},{262981,826808,237936},{267002,438552,871785},{271467,592302,176734},{279209,880256,370636},{290082,738236,759585},{308221,553121,306184},{327430,380670,616140},{329228,579494,219292},{335392,940063,872524},{342607,789259,647865},{343982,610053,473003},{351572,710271,234191},{355001,378517,833822},{356988,988350,98517},{373321,727994,205027},{399866,834707,234731},{409207,934136,596974},{412413,593104,628468},{421212,864982,982652},{442333,923180,176317},{442946,801401,799978},{447411,715053,155265},{461090,933427,360992},{471549,665260,955076},{499921,587218,130159},{502274,906023,272847},{505897,821494,809118},{512428,705462,965658},{535788,541703,92423},{555687,960113,583425},{564227,805666,405427},{575074,612835,899325},{629330,797045,84784},{629718,828687,181137},{640143,711963,50666},{643572,832599,301279},{693513,835058,190184},{707821,925059,8606},{720443,971000,127523},{738423,953410,112865},{819134,914252,730415}});
    }
}
