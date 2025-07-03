export const Times:React.FC<{width: number}> = (props) => {
    return (
        <div className="d-inline-flex position-absolute w-100 h-100">
            <div className="d-inline-flex" style={{ width: `${props.width}%` }}></div>
            {(() => {
                const times = [];
                for (let i = 0; i < 24; i++) {
                    times.push(<div className="d-flex h-100 " style={{ width: "calc(100% / 24)" }}>
                        {i < 10 ?
                            `0${i}:00`
                            :
                            `${i}:00`}
                    </div>);
                }
                return times;
            })()}
        </div>
    );
}
export default Times;