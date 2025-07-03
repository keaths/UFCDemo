import Times from "./Times/Times";
import Day, { Metric } from "./Day/Metric";

export const TimeTable:React.FC<{metric: String, width: number}> = (props) => {
    return (
        <div className="container-fluid bg-danger w-100 p-0" style={{ height: "100%" }}>
            <div className="bg-danger w-100 p-0" style={{ height: "10%" }}>This Weeks Schedule</div>
            <div className="bg-success w-100 p-0" style={{ height: "90%" }}>
                <div className="w-100 h-100 p-0 position-relative">
                    <Times width={6} />
                    {(() => {
                        const stuff = [];
                        for (let i = 0; i < 7; i++) {
                            stuff.push(<Metric metric={props.metric} />);
                        }
                        return stuff;
                    })()}
                </div>
            </div>
        </div>
    );
}

export default TimeTable;