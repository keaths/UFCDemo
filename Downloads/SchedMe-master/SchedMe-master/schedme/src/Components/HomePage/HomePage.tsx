import Navbar from "../SideNav/SideNav";
import TimeTable from "../TimeTable/TimeTable";
import Day from "../TimeTable/Day/Metric";
import Scheds from "../TimeTable/Scheds/Scheds";
import Times from "../TimeTable/Times/Times";

export const HomePage = () => {
    return (
        <div className="container-fluid">
            <div className="row">
                <Navbar />
                <div className="col bg-warning p-0 d-flex align-items-center p-5" style={{ height: "100vh" }}>
                    <TimeTable metric={"Days"} width={4}/>
                </div>
            </div>
        </div>

    );
}