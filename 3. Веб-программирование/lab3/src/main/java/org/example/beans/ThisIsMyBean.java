package org.example.beans;

public class ThisIsMyBean {

    private Model model;

    private Point point;

    private InfoBean infoBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    public void doExtend() {
        ExtendedPoint extendedPoint = new ExtendedPoint();
        extendedPoint.setX(point.getX());
        extendedPoint.setY(point.getY());
        extendedPoint.setR(point.getR());
        extendedPoint.selfEval();
        model.add(extendedPoint);
        System.out.println(extendedPoint);

        infoBean.registerShot(extendedPoint.getR(), extendedPoint.getStatus());

    }

}
