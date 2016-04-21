import java.util.Scanner;

/**
 * Created by  Administrator Joe Wang, tbpwang@gmail.com
 * 2016/4/20.
 */
public class Subdivision {
    /**
     * * 剖分类，根据剖分的层次，对三角形行列号和经纬度进行换算
     *
     * @param level
     * 剖分层次，[0, n - 1]
     * @param row
     * 行号，[1, n]
     * @param column
     * 列号，[1, 2 * row - 1]
     * @param FROM_LONGITUDE
     * 常量，设置剖分的初始经度，用百分制角度表示
     * @param currentLatitude
     * 纬度，用百分制角度表示
     * @param currentLongitude
     * 经度，用百分制角度表示
     * @param currentRow
     * 待求三角形所在的行数
     * @param currentColumn
     * 待求三角形所在的列数
     *
     */

    private static final int FROM_LONGITUDE = 0;
    private int level;
    private int currentRow;
    private int currentColumn;

    public Subdivision(int level) {
        if (level >= 0 && level <= 20) {
            this.level = level;
        } else if (level < 0) {
            System.out.println("请输入整数剖分层次");
            System.exit(0);
        }else if (level > 20) {
            System.out.println("输入的剖分层数过大，请输入[0，20]的整数");
            System.exit(0);
        }
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public Coordinate getCoordinate() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(90 * (1 - getCurrentRow() * Math.pow(2, - this.level)));
        coordinate.setLongitude(FROM_LONGITUDE + 90 * Math.pow(2, - this.level) * ((getCurrentColumn() + 1) / 2));
        return coordinate;
    }

    public int maxRow() {
        return (int)(Math.pow(2, level));
    }

    public int maxColumn(int row) {
        return (2 * row - 1);
    }

    @Override
    public String toString() {
        return "对应经纬度坐标为(单位：度)：(" + getCoordinate().getLongitude() + ", " + getCoordinate().getLatitude() + ")";
    }

    class Coordinate {
        /**
         * @param latitude
         * @param longitude
         *
         */
        double latitude;
        double longitude;

        double getLatitude() {
            return latitude;
        }

        void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        double getLongitude() {
            return longitude;
        }

        void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static void main(String[] args) {
        System.out.println("输入剖分层数[0, n - 1]： ");
        Scanner scanner = new Scanner(System.in);
        int level = scanner.nextInt();
        Subdivision subdivision = new Subdivision(level);
        int maxRow;
        System.out.println("输入三角形所在的行(行数取值[1, " + (maxRow = subdivision.maxRow()) + "])");
        scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int maxColumn = 0;
        if (i <= maxRow && i >= 0) {
            subdivision.setCurrentRow(i);
            System.out.println("输入三角形所在的列(列的取值范围为[1, " + (maxColumn = subdivision.maxColumn(i))  + "]):");
        } else {
            System.out.println("输入了错误行数！");
            System.exit(0);
        }
        int j = scanner.nextInt();
        if (j >= 1 && j <= maxColumn) {
            subdivision.setCurrentColumn(j);
        } else {
            System.out.println("输入了错误的列数！");
            System.exit(0);
        }

        //计算并输出
        System.out.println(subdivision.toString());

    }
}


