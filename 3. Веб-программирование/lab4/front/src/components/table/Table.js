import React from "react";
import PointService from "../../service/PointService";
import {connect, useDispatch} from "react-redux";
import TableRow from "./TableRow";
import ReactPaginate from "react-paginate";
import pointUpdater from "../../service/PointUpdater";
import {setCurrentPage} from "../../redux/pointSlice";


class LocalTable extends React.Component {

    constructor(props) {
        super(props);
        this.handlePageClick = this.handlePageClick.bind(this);
    }

    handlePageClick(event){
        console.log("я пришёл из пагинатора ", event.selected);
        pointUpdater(this.props.dispatch, this.props.pointService, this.props.jwtToken, event.selected);
        setCurrentPage(event.selected);
    }

    componentDidMount() {
        pointUpdater(this.props.dispatch, this.props.pointService, this.props.jwtToken, 0);
    }

    render() {
        return (
            <div className={"col s12"}>
                <div className={"row"}>

                    <table className={"col s12 push-s0 m10 push-m1 xl10 push-xl1 centered striped"}>

                            <tbody>
                                <tr className={"row"}>
                                    <td className={"col s1 header-line"}>X</td>
                                    <td className={"col s1 header-line"}>Y</td>
                                    <td className={"col s1 header-line"}>R</td>
                                    <td className={"col s2 header-line"}>Статус</td>
                                    <td className={"col s4 header-line"}>Время запроса</td>
                                    <td className={"col s3 header-line"}>Время работы</td>
                                </tr>

                                {this.props.points.map(row => (
                                    <TableRow row={row} />
                                ))}
                            </tbody>

                    </table>
                </div>
                <div className={"row"}>
                    <div className={"col s12 center-align"}>
                        <ReactPaginate className={"pagination"}
                                       pageCount={ this.props.totalPages }
                                       breakLabel="..."
                                       nextLabel="Туда"
                                       onPageChange={this.handlePageClick}
                                       pageRangeDisplayed={5}
                                       previousLabel="Сюда"
                                       renderOnZeroPageCount={null}
                                       pageClassName={"waves-light"}
                                       activeClassName={"active"}
                                       disabledClassName={"disabled"}
                                       disableInitialCallback={false}
                        />
                    </div>

                </div>
            </div>

        )
    }

}

const PatchedLocalTable = (props) => {
    const dispatch = useDispatch();
    const pointService = new PointService();
    return <LocalTable pointService={pointService} dispatch={dispatch} {...props} />
}

const mapStateToProps = function(state) {
    return {
        jwtToken: state.user.jwtToken,
        points: state.point.points,
        totalPages: state.point.totalPages,

    }
}

const Table = connect(
    mapStateToProps
)(PatchedLocalTable);

export default Table;