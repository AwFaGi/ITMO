import React from "react";
import TableCell from "./TableCell";

class TableRow extends React.Component{

    render() {
        return (
            <tr className={"row"}>
                <TableCell value={this.props.row.x} class={"col s1"}/>
                <TableCell value={this.props.row.y} class={"col s1"}/>
                <TableCell value={this.props.row.r} class={"col s1"}/>
                <TableCell value={this.props.row.status ? "Hit" : "Miss"} class={"col s2"}/>
                <TableCell value={new Date(this.props.row.timeStamp).toLocaleString("ru-RU", {
                    hour12: false
                })} class={"col s4"}/>
                <TableCell value={this.props.row.workingTime} class={"col s3"}/>
            </tr>
        )
    }

}

export default TableRow;