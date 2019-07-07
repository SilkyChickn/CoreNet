package mnist;

public class MnistDigit {

    private int label;
    private int[][] data;
    private float[] dataArray;

    MnistDigit(int label, int[][] data){
        this.label = label;
        this.data = data;

        //Store data as float array
        this.dataArray = new float[data.length * data[0].length];
        int counter = 0;
        for(int[] ba: data){
            for(int b: ba){
                this.dataArray[counter++] = b / 255.0f;
            }
        }
    }

    int getLabel() {
        return label;
    }

    public int[][] getData() {
        return data;
    }

    float[] getDataArray() {
        return dataArray;
    }
}
