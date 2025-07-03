import Navbar from "../SideNav/SideNav";
import TimeTable from "../TimeTable/TimeTable";
import SingleGroup from "./SingleGroup/SingleGroup";

export const MyGroups: React.FC<{}> = () => {
    return (
        <div className="container-fluid">
            <div className="row">
                <Navbar />
                <div className="col bg-warning p-0 d-flex align-items-center p-5" style={{ height: "100vh" }}>
                    <div className="container-fluid h-100 bg-success p-0" style={{ width: "100%" }}>
                        <div className="bg-danger" style={{ height: "15%" }}>My Groups</div>
                        <div className="d-inline-flex w-100 border border-black p-2" style={{ height: "calc(80%/3)" }}>
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                        </div>
                        <div className="d-inline-flex w-100 border border-black p-2" style={{ height: "calc(80%/3)" }}>
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                        </div>
                        <div className="d-inline-flex w-100 border border-black p-2" style={{ height: "calc(80%/3)" }}>
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                            <SingleGroup />
                        </div>
                        <div className="w-100 bg-info justify-content-center align-items-center" style={{height:"5%"}}>
                            <div className="d-inline-flex">left</div>
                            <div className="d-inline-flex"> right</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}
export default MyGroups;