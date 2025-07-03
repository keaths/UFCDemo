import Times, { Scheds } from "../Scheds/Scheds";

export const Metric:React.FC<{metric: String}> = (props) => {
    return (
        <div className="d-inline-flex w-100 bg-danger" style={{ height: "calc(100% / 7)" }}>
            <div className="d-flex justify-content-center align-items-center border border-black" style={{width:"6%"}}>{props.metric}</div>
            <Scheds/>
        </div>
    );
}
export default Metric;