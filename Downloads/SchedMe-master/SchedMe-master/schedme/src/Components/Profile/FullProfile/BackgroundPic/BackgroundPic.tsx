export const BackgroundPic: React.FC<{}> = (props) => {
    return (
        <div className="position-absolute h-100 bg-danger w-100">
            <div className="d-flex justify-content-center align-items-center h-75 border border-black">background</div>
            <div className="d-flex align-items-start h-25 w-100 border border-black">
                <div className="ms-auto d-flex w-50 border border-black h-100">
                    <div className="d-flex ms-auto border border-black" style={{ width: "40%" }}>
                        <div className="w-50 bg-info rounded-4 h-25 m-2">test</div>
                        <div className="w-50 bg-info rounded-4 h-25 m-2">test</div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default BackgroundPic;