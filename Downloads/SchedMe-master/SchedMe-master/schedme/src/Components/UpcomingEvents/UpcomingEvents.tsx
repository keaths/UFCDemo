import Post from "../Feed/Post/Post";
import Navbar from "../SideNav/SideNav";
import EventPost from "./EventPost/EventPost";

export const UpcomingEvents: React.FC<{}> = (props) => {
    return (
        <div className="container-fluid">
            <div className="row">
                <Navbar />
                <div className="col bg-warning p-0 align-items-center p-5" style={{ height: "100vh" }}>
                    <div className="container-fluid justify-content-start border border-black bg-light p-0" style={{ width: "100%" }}>
                        <div className="justify-content-center align-items-center bg-danger mb-5" style={{ height: "100px" }}>testerer</div>
                        <EventPost/>
                        <EventPost/>
                        <EventPost/>

                    </div>
                </div>
            </div>
        </div>
    )
}
export default UpcomingEvents;