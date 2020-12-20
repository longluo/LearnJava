package com.imlongluo.insertData;

import java.util.ArrayList;

public class InsertData {
    public static final int AD_GDT = 0;
    public static final int AD_MOBISAGE = 1;
    public static final int AD_BAIDU = 2;

    private static int[] mAdsRatio = {
            3, 1, 1};

    private static int[] mAdsLoadedFlag = {
            0, 0, 0};

    private static int adRatioSum = mAdsRatio[AD_GDT] + mAdsRatio[AD_MOBISAGE] + mAdsRatio[AD_BAIDU];

    private static ArrayList mAdItemDatas = new ArrayList();

    private static ArrayList mGdtDatas = new ArrayList();
    private static ArrayList mMobisageDatas = new ArrayList();
    private static ArrayList mBdAdDatas = new ArrayList();

    public static void main(String[] args) {
        initDatas();

        printListValue(mAdItemDatas);

//        updateDatas(mGdtDatas);
        updateDatas(AD_GDT, mGdtDatas);
        printListValue(mAdItemDatas);
        mAdsLoadedFlag[AD_GDT] = 1;

//        updateDatas(mMobisageDatas);
        updateDatas(AD_MOBISAGE, mMobisageDatas);
        printListValue(mAdItemDatas);
        mAdsLoadedFlag[AD_MOBISAGE] = 1;

//        updateDatas(mBdAdDatas);
        updateDatas(AD_BAIDU, mBdAdDatas);
        printListValue(mAdItemDatas);
        mAdsLoadedFlag[AD_BAIDU] = 1;
    }

    private static void printListValue(ArrayList list) {
        for (int index = 0; index < list.size(); index++) {
            System.out.printf(list.get(index) + " ");
        }

        System.out.printf("\n" + "size=" + list.size() + "\n");
    }

    private static void initDatas() {
        for (int i = 0; i < 10; i++) {
            mGdtDatas.add(5);
            mMobisageDatas.add(6);
            mBdAdDatas.add(7);
        }
    }

    private static void updateDatas(ArrayList datas) {
        if (0 == mAdsLoadedFlag[AD_GDT] && 0 == mAdsLoadedFlag[AD_MOBISAGE] && 0 == mAdsLoadedFlag[AD_BAIDU]) {
            mAdItemDatas.addAll(datas);
        }

        if (0 == mAdsLoadedFlag[AD_GDT] && 1 == mAdsLoadedFlag[AD_MOBISAGE] && 1 == mAdsLoadedFlag[AD_BAIDU]) {
            if (0 == mAdItemDatas.size()) {
                mAdItemDatas.addAll(datas);
                return;
            }

            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(mAdItemDatas);
            mAdItemDatas.clear();
            int size = temp.size();

            for (int i = 0; i < size; ) {
                for (int j = 0; j < mAdsRatio[AD_GDT]; j++) {
                    mAdItemDatas.add(datas.get(0));
                    datas.remove(0);
                    i++;
                    if (i == size) {
                        break;
                    }
                }

                for (int k = 0; k < (adRatioSum - mAdsRatio[AD_GDT]); k++) {
                    if (temp.size() > 0) {
                        mAdItemDatas.add(temp.get(0));
                        temp.remove(0);
                    } else {
                        break;
                    }
                }

/*                for (int m = 0; m < mAdsRatio[AD_BAIDU]; m++) {
                    if (datas.size() > 0) {
                        mAdItemDatas.add(datas.get(0));
                        datas.remove(0);
                    } else {
                        break;
                    }
                }*/
            }
        }

        if (1 == mAdsLoadedFlag[AD_GDT] && 0 == mAdsLoadedFlag[AD_MOBISAGE] && 1 == mAdsLoadedFlag[AD_BAIDU]) {
            if (0 == mAdItemDatas.size()) {
                mAdItemDatas.addAll(datas);
                return;
            }
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(mAdItemDatas);

            mAdItemDatas.clear();

            int size = datas.size();

            for (int i = 0; i < size; ) {
                for (int j = 0; j < mAdsRatio[AD_MOBISAGE]; j++) {
                    mAdItemDatas.add(datas.get(0));
                    datas.remove(0);
                    i++;
                    if (i == size) {
                        break;
                    }
                }

                for (int k = 0; k < (adRatioSum - mAdsRatio[AD_MOBISAGE]); k++) {
                    if (temp.size() > 0) {
                        mAdItemDatas.add(temp.get(0));
                        temp.remove(0);
                    } else {
                        break;
                    }
                }

/*                for (int m = 0; m < mAdsRatio[AD_BAIDU]; m++) {
                    if (temp.size() > 0) {
                        mAdItemDatas.add(temp.get(0));
                        temp.remove(0);
                    } else {
                        break;
                    }
                }*/
            }
        }

        // Baidu AD
        if (1 == mAdsLoadedFlag[AD_GDT] && 1 == mAdsLoadedFlag[AD_MOBISAGE] && 0 == mAdsLoadedFlag[AD_BAIDU]) {
            if (1 == mAdItemDatas.size()) {
                mAdItemDatas.addAll(datas);
                return;
            }

            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(mAdItemDatas);
            mAdItemDatas.clear();

            int size = datas.size();

            for (int i = 0; i < size; ) {
                for (int j = 0; j < mAdsRatio[AD_BAIDU]; j++) {
                    mAdItemDatas.add(datas.get(0));
                    datas.remove(0);
                    i++;
                    if (i == size) {
                        break;
                    }
                }

                for (int k = 0; k < (adRatioSum - mAdsRatio[AD_BAIDU]); k++) {
                    if (temp.size() > 0) {
                        mAdItemDatas.add(temp.get(0));
                        temp.remove(0);
                    } else {
                        break;
                    }
                }

/*                for (int m = 0; m < mAdsRatio[AD_BAIDU]; m++) {
                    if (temp.size() > 0) {
                        mAdItemDatas.add(temp.get(0));
                        temp.remove(0);
                    } else {
                        break;
                    }
                }*/

            }
        }
    }

    private static void updateDatas(int adType, ArrayList<Integer> datas) {
        System.out.println("updateDatas, size=" + datas.size() + ",adType=" + adType);

        if (0 == mAdsLoadedFlag[AD_GDT] && 0 == mAdsLoadedFlag[AD_MOBISAGE] && 0 == mAdsLoadedFlag[AD_BAIDU]) {
            mAdItemDatas.addAll(datas);
        }

        switch (adType) {
            case AD_GDT:
                if ((1 == mAdsLoadedFlag[AD_GDT]) || (mAdItemDatas.size() == 0)) {
                    mAdItemDatas.addAll(datas);
                    return;
                }

                ArrayList temp = new ArrayList();
                temp.addAll(mAdItemDatas);
                mAdItemDatas.clear();
                int size = datas.size();

                for (int i = 0; i < size; ) {
                    for (int j = 0; j < mAdsRatio[AD_GDT]; j++) {
                        mAdItemDatas.add(datas.get(0));
                        datas.remove(0);
                        i++;
                        if (i == size) {
                            break;
                        }
                    }

                    for (int k = 0; k < (adRatioSum - mAdsRatio[AD_GDT]); k++) {
                        if (temp.size() > 0) {
                            mAdItemDatas.add(temp.get(0));
                            temp.remove(0);
                        } else {
                            break;
                        }
                    }
                }
                break;

            case AD_MOBISAGE:
                if ((1 == mAdsLoadedFlag[AD_MOBISAGE]) || (mAdItemDatas.size() == 0)) {
                    mAdItemDatas.addAll(datas);
                    return;
                }

                ArrayList temp2 = new ArrayList();
                temp2.addAll(mAdItemDatas);
                mAdItemDatas.clear();
                int size2 = datas.size();

                for (int i = 0; i < size2; ) {
                    for (int j = 0; j < mAdsRatio[AD_MOBISAGE]; j++) {
                        mAdItemDatas.add(datas.get(0));
                        datas.remove(0);
                        i++;
                        if (i == size2) {
                            break;
                        }
                    }

                    for (int k = 0; k < (adRatioSum - mAdsRatio[AD_MOBISAGE]); k++) {
                        if (temp2.size() > 0) {
                            mAdItemDatas.add(temp2.get(0));
                            temp2.remove(0);
                        } else {
                            break;
                        }
                    }
                }
                break;

            case AD_BAIDU:
                if ((1 == mAdsLoadedFlag[AD_BAIDU]) || (mAdItemDatas.size() == 0)) {
                    mAdItemDatas.addAll(datas);
                    return;
                }

                ArrayList temp3 = new ArrayList();
                temp3.addAll(mAdItemDatas);
                mAdItemDatas.clear();
                int size3 = datas.size();

                for (int i = 0; i < size3; ) {
                    for (int j = 0; j < mAdsRatio[AD_BAIDU]; j++) {
                        mAdItemDatas.add(datas.get(0));
                        datas.remove(0);
                        i++;
                        if (i == size3) {
                            break;
                        }
                    }

                    for (int k = 0; k < (adRatioSum - mAdsRatio[AD_BAIDU]); k++) {
                        if (temp3.size() > 0) {
                            mAdItemDatas.add(temp3.get(0));
                            temp3.remove(0);
                        } else {
                            break;
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

}
