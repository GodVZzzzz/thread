package thread;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zhang
 * @date 2018/8/2
 * @Description  银行家算法，五个进程，四个资源
 */
public class BankerAlgorithm {

    private static final Character[] process = {'A','B','C','D','E'};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /*已分配资源数目矩阵*/
        int hadDone[][] = new int[5][4];

        /*仍需要资源数目矩阵*/
        int need[][] = new int[5][4];

        /*现有资源*/
        int[] E = new int[4];

        /*已分配资源*/
        int[] P = new int[4];

        /*可用资源,A = E - P*/
        int[] A = new int[4];

        System.out.println("请输入已分配矩阵数据(e.g. 3 0 1 1 0 1 0 0 1 1 1 0 1 1 0 1 0 0 0 0)：");
        for(int i = 0; i< 5; i++){
            for(int j = 0; j< 4; j++){
                hadDone[i][j] = in.nextInt();
            }
        }

        System.out.println("请输入仍需要资源矩阵数据(e.g. 1 1 0 0 0 1 1 2 3 1 0 0 0 0 1 0 2 1 1 0)：");
        for(int i = 0; i< 5; i++){
            for(int j = 0; j< 4; j++){
                need[i][j] = in.nextInt();
            }
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                P[i] += hadDone[j][i];
            }
        }

        System.out.println("请输入总的资源数目(e.g. 6 3 4 2)：");
        for(int i = 0; i < 4; i++){
            E[i] += in.nextInt();
        }

        for(int i = 0; i < 4; i++){
            A[i] = E[i] - P[i];
            if(A[i] < 0){
                System.out.println("请输入正确的矩阵，A矩阵不可为负！");
                return;
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        int size = arrayList.size();
        int i = 0;
        while(i< 5 ){
            for (Integer anArrayList : arrayList) {
                while (i == anArrayList) {
                    i++;
                }
            }
            if(i == 5)
                break;
            int s[] = need[i];
            int t[] = hadDone[i];
            int j = 0;
            while (j < 4){
                if(s[j] > A [j]){
                    if(i == 4 ){
                        System.out.println("发生死锁，当前进程为"+process[i]);
                        return;
                    }
                    else {
                        i++;
                        break;
                    }
                }
                else {
                    j++;
                    if(j == 4){
                        for(int h = 0; h < 4; h++){
                            A[h] += t[h];
                        }

                        System.out.println("进程"+process[i]+"已安全运行并退出\n");
                        arrayList.add(i);
                        i = 0;
                        for (Integer anArrayList : arrayList) {
                            while (anArrayList == i) {
                                i++;
                            }
                        }

                    }
                }

            }


        }


    }

}
