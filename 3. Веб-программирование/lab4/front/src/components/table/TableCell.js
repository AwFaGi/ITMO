import React from "react";

class TableCell extends React.Component{

    render() {
        return (
            <td className={this.props.class}>
                {this.props.value}
            </td>
        )
    }
}

export default TableCell;