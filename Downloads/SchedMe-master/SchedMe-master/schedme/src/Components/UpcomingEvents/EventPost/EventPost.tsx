import Content from "../../Feed/Post/Content/Content";
import Header from "../../Feed/Post/Header/Header";
import ReactionJoin from "./ReactionJoin/ReactionJoin";

export const EventPost: React.FC<{}> = (props) => {
    return (
        <div className="d-flex justify-content-center mb-5">
            <div className="d-flex bg-info rounded-3 mb-5" style={{ width: "75%" }}>
                <div className="w-100 h-100">
                    <Header />
                    <Content />
                    <ReactionJoin />
                </div>
            </div>
        </div>
    )
}
export default EventPost;