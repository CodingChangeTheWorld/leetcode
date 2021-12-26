package sorAlgorithms;

import java.util.Arrays;

/**
 * AUTHOR:KEPM
 * TIME:2021/12/26
 */
public class SortAlgorithms {
    //冒泡排序
    public int[] bubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++) {
            for (int j = 0; j < arr.length - 1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr,j,j+1);
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序 不稳定排序
     * 未排序的数组中选择最大的元素放在数组最后面
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = 0;
            for (int j = 0; j <= arr.length - 1 - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(arr, maxIndex, arr.length - 1 - i);
        }
        return arr;
    }

    /**
     * 插入排序
     * 默认数组分为有序和无序两部分,遍历无序部分，将其插入有序部分
     * @param arr
     * @return
     */
    public int[] insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int j=i;
            while(j>=1 && arr[j-1]>arr[j]) {
                swap(arr, j-1, j );
                j--;
            }
        }
        return arr;
    }

    /**
     * 分而治之 拆分成每个最小有序的子序列,在进行合并
     * @param arr
     * @return
     */
    public int[] mergeSort(int[] arr){
        int i=0;
        int j=arr.length-1;
        int[] auxiliaryArray=new int[arr.length];
        split(arr,i,j,auxiliaryArray);
        return arr;
    }

    private void merge(int[] arr, int i, int mid, int j,int[] auxiliaryArray) {
        int leftIndex=i;
        int rightIndex=mid+1;
        int tmpIndex=0;
        while(leftIndex<=mid && rightIndex<=j){
            if(arr[leftIndex]<=arr[rightIndex]){
                auxiliaryArray[tmpIndex++]=arr[leftIndex++];
            }else{
                auxiliaryArray[tmpIndex++]=arr[rightIndex++];
            }
        }
        while(leftIndex<=mid){
            auxiliaryArray[tmpIndex++]=arr[leftIndex++];
        }
        while(rightIndex<=j){
            auxiliaryArray[tmpIndex++]=arr[rightIndex++];
        }
        tmpIndex=0;
        while(i<=j){
            arr[i++]=auxiliaryArray[tmpIndex++];
        }
    }

    private void split(int[] arr, int i, int j,int[] auxiliaryArray) {
        if(i>=j) return;
        int mid=i+(j-i)/2;
        split(arr,i,mid,auxiliaryArray);
        split(arr,mid+1,j,auxiliaryArray);
        merge(arr,i,mid,j,auxiliaryArray);

    }

    /**
     * 快速排序 每次确定一个元素的最终位置。选择一个锚点,比他小的放左边,比他大的放右边
     * @param arr
     * @return
     */
    public int[] quickSort(int[] arr){
        quickSortImpl(arr,0,arr.length-1);
        return arr;
    }

    private void quickSortImpl(int[] arr, int i, int j) {
        if(i>=j) return;
        int anchor=arr[i];
        int leftIndex=i;
        int rightIndex=j;
        while(leftIndex<rightIndex){
            while(leftIndex<rightIndex && arr[rightIndex]>=anchor){
                rightIndex--;
            }
            if(leftIndex<rightIndex){
                swap(arr,leftIndex,rightIndex);
            }
            while(leftIndex<rightIndex && arr[leftIndex]<=anchor){
                leftIndex++;
            }
            if(leftIndex<rightIndex){
                swap(arr,leftIndex,rightIndex);
            }
        }
        quickSortImpl(arr,i,leftIndex-1);
        quickSortImpl(arr,leftIndex+1,j);
    }

    /**
     * 堆排序
     * 1、插入元素构造大顶堆
     * 2、将堆顶元素放在组数最后
     * 3、调整剩下元素使其符合大顶堆结构
     * 3、循环2、3
     * @param arr
     * @return
     */
    public int[] heapSort(int[] arr){
        buildBigHeap(arr);
        int heapSize=arr.length;
        while(heapSize>=1) {
            swap(arr, 0, --heapSize);
            adjustHeap(arr,0, heapSize);
        }
        return arr;

    }

    private void adjustHeap(int[] arr,int index, int heapSize) {
        int leftChild=2*index+1;
        int rightChild=2*index+2;
        int biggerChild=leftChild;
        while(leftChild<heapSize){
            if(rightChild<heapSize && arr[rightChild]>arr[leftChild]){
                biggerChild=rightChild;
            }
            if(arr[biggerChild]>arr[index]){
                swap(arr,biggerChild,index);
            }
            index=biggerChild;
            leftChild=2*index+1;
            rightChild=2*index+2;
            biggerChild=leftChild;
        }
    }

    private void buildBigHeap(int[] arr) {
        for(int i=0;i<arr.length;i++){
            while(arr[i]>arr[i/2]){
                swap(arr,i,i/2);
                i=i/2;
            }
        }
    }

    public void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


}
